package com.asanast.gymfit.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/registrarse")
	public String registrarse(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result, Model model) {
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors().toString());
			return "registro";
		}
		usuarioService.save(usuario);
		return "redirect:registro?valido=si";
	}

}
