package com.asanast.gymfit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.asanast.gymfit.service.CustomUserDetailsService;
import com.asanast.gymfit.util.CustomAuthenticationProvider;
import com.asanast.gymfit.util.CustomAuthenticationSuccessHandler;

/**
 * Clase de configuración de Spring Security
 * @author antonio
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Propiedad que implementa el sistema personalizado de autenticación a la aplicación
	 */
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;

	/**
	 * Metodo de configuracion global para Spring Security
	 * @param auth el manjeador de la autenticación
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

	/**
	 * Metodo de configuracion de de las peticiones y roles de Hibernate, así como el login y logout.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		
		http.authorizeRequests().antMatchers("/home/**").hasAuthority("ROL_REGISTRADO")	
		.and().formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/home")
				.loginPage("/login").defaultSuccessUrl("/home").failureUrl("/login?error").successHandler(customAuthenticationSuccessHandler()).usernameParameter("usuario").passwordParameter("clave")
				.and().logout().clearAuthentication(true).logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/login")
				.and().csrf().and().exceptionHandling()
				.accessDeniedPage("/403");
	}
	
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
	
	/**
	 * Bean personalizado para la obtencion de los datos de usuario a partir de la implementacion de la interface UserDetailService
	 * @return CustomUserDetailsService
	 */
	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	/**
	 * Bean de autenticación satisfactoria
	 * @return CustomAuthenticationSuccessHandler
	 */
	@Bean
	@Autowired
	public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}

}
