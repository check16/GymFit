package com.asanast.gymfit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asanast.gymfit.pojo.Usuario;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String irLogin() {
		return "login";
	}
	
	@RequestMapping("/registro")
	public String irRegistro(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "registro";
	}
	
	@RequestMapping("/403")
	public String errorAcceso() {
		return "403";
	}

}
