package com.asanast.gymfit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.pojo.Usuario;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Usuario usuario = usuarioService.findByLogin(username);
		
		if(usuario != null){
			authorities.add(new SimpleGrantedAuthority(usuario.getRol().getNombreRol()));
			User user = new User(usuario.getLogin(),usuario.getClave(),authorities);
			return user;
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}
}
