package com.asanast.gymfit.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.UsuarioService;

/**
 * Clase que implementa el AuthenticationSuccessHandler para una correcta
 * autenticacion
 * 
 * @author antonio
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	/**
	 * Propiedad que encapsula el servicio del usuario
	 */
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Metodo sobrescrito para llevar a cabo una serie de procesos una vez la
	 * autenticacion es correcta. Se pone el usuario en la sesion y se devuelve
	 * al home
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.findByLogin(user);

		session.setAttribute("usuario", usuario);
		session.setAttribute("authorities", authentication.getAuthorities());

		response.setStatus(HttpServletResponse.SC_OK);

		response.sendRedirect(request.getContextPath() + "/home");

	}

}
