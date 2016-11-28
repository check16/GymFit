package com.asanast.gymfit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asanast.gymfit.service.TipoEjercicioService;

@Controller
public class EjerciciosController {
	
	@Autowired
	private TipoEjercicioService tipoEjercicioService;
	
	@RequestMapping("/home/aprendeEjercicios")
	public String irAprenderEjercicios(Model model) {
		model.addAttribute("activo", "ejercicios");
		model.addAttribute("ejercicios", tipoEjercicioService.findAll());
		return "aprendeEjercicios";
	}

}
