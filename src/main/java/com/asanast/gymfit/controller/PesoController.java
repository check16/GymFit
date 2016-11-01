package com.asanast.gymfit.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.PesoService;

@Controller
public class PesoController {
	
	@Autowired
	private PesoService pesoService;
	
	@RequestMapping("/home/registrarPeso")
	public String irRegistrarPeso(Model model, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		Peso ultimoPeso = pesoService.findLastPeso(usuario);
		if(ultimoPeso != null) {
			model.addAttribute("ultimoPeso", pesoService.findLastPeso(usuario));
			model.addAttribute("peso", new Peso());
		}else {
			model.addAttribute("ultimoPeso", new Peso());
			model.addAttribute("peso", new Peso());
		}
		return "registrarPeso";
	}
	
	@RequestMapping("/home/registrarPeso/registrar")
	public String registrarPeso(@Valid @ModelAttribute ("peso") Peso peso) {
		// TODO Realizar el metodo y comprobar la que no exista entreno
		return "redirect:/home/registrarPeso";
	}

}
