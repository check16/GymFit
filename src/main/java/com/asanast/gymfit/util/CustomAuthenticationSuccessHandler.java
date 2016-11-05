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

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.findByLogin(user);
        
        session.setAttribute("usuario", usuario);
        session.setAttribute("authorities", authentication.getAuthorities());

        response.setStatus(HttpServletResponse.SC_OK);
 
        response.sendRedirect("/home");
		
	}

}
