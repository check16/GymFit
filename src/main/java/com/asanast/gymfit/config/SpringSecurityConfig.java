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

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

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

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}
	
	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	@Autowired
	public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}

}
