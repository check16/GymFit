package com.asanast.gymfit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.asanast.gymfit.service.CustomUserDetailsService;
/**
 * Clase para la implementacion del AuthenticationProvider para que se realice de forma personalizada
 * @author antonio
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	/**
	 * Propiedad que encapsula el servicio del detail service
	 */
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	/**
	 * Propiedad que encapsula el encoder de la contraseña
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Metodo que sobreescribe la authenticación personalizada del usuario en la aplicacion
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				//Datos que proceden del formulario
				String principal = authentication.getName();
				String credentials = (String) authentication.getCredentials();

				User user = (User) customUserDetailsService.loadUserByUsername(principal);
				if (user != null) {
					if (bCryptPasswordEncoder.matches(credentials, user.getPassword())) {
						return new UsernamePasswordAuthenticationToken(principal, credentials, user.getAuthorities());
					}else {
						throw new BadCredentialsException("Error de login");
					}
				} else {
					throw new BadCredentialsException("Error de login");
				}
	}
	
	/**
	 * Metodo que indica el soporte para la autenticacion
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
