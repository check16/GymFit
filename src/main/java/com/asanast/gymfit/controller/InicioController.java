package com.asanast.gymfit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
		return "redirect:/login";
	}
	
	@RequestMapping("/home")
	public String mostrarHome(Model model, HttpSession sesion) {
		return "home";
	}

}
