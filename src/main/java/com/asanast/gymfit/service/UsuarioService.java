package com.asanast.gymfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.RolDao;
import com.asanast.gymfit.dao.UsuarioDao;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.util.Constantes;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RolDao rolDao;
	
	public void save(Usuario usuario) {
		usuario.setRol(rolDao.findById(Constantes.ROL_REGISTRADO));
		String claveUsr = usuario.getClave();
		usuario.setClave(passwordEncoder.encode(claveUsr));
		usuarioDao.save(usuario);
	}
	
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}
	
	public Usuario findById(int id) {
		return usuarioDao.findById(id);
	}
	
	public Usuario findByLogin(String login) {
		return usuarioDao.findByLogin(login);
	}
	
	public void update(Usuario usuario) {
		usuarioDao.update(usuario);
	}
	
	public void delete(Usuario usuario) {
		usuarioDao.delete(usuario);
	}
	
	public void saveOrUpdate(Usuario usuario) {
		if(usuario.getIdUsuario() == 0) {
			usuarioDao.save(usuario);
		}else {
			usuarioDao.update(usuario);
		}
	}

}
