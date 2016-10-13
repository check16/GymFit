package com.asanast.gymfit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public String mostrarHome(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String nombre = auth.getName();
		model.addAttribute("usuario", usuarioService.findByLogin(nombre));
		return "home";
	}

}
