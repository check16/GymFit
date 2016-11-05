package com.asanast.gymfit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.asanast.gymfit.service.UsuarioService;

@Controller
@SessionAttributes("usuario")
public class InicioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping("/")
	public String acceso() {
		return "redirect:home";
	}
	
	@RequestMapping("/home")
	public String mostrarHome(Model model, HttpSession sesion) {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		sesion.setAttribute("usuario", usuarioService.findByLogin(login));
		return "home";
	}

}
