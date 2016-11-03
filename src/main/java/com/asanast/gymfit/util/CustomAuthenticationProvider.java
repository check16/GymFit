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

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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
	
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
