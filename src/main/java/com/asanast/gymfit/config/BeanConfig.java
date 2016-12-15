package com.asanast.gymfit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.asanast.gymfit.util.CustomAuthenticationProvider;

@Configuration
public class BeanConfig {

	/**
	 * Bean de encode para la encriptacion
	 * @return encoder, el encoder para la encriptación
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	/**
	 * Bean del proveedor de autenticacion personalizado
	 * @return
	 */
	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}
}
