package com.asanast.gymfit.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.PesoService;

@Controller
public class PesoController {
	
	@Autowired
	private PesoService pesoService;
	
	@ModelAttribute
	public void init(Model model, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		Peso ultimoPeso = pesoService.findLastPeso(usuario);
		if(ultimoPeso != null) {
			model.addAttribute("ultimoPeso", pesoService.findLastPeso(usuario));
			model.addAttribute("peso", new Peso());
		}else {
			model.addAttribute("ultimoPeso", new Peso());
			model.addAttribute("peso", new Peso());
		}
	}
	
	@RequestMapping("/home/registrarPeso")
	public String irRegistrarPeso(Model model) {
		return "registrarPeso";
	}
	
	@RequestMapping("/home/registrarPeso/registrar")
	public String registrarPeso(@Valid @ModelAttribute ("peso") Peso peso, BindingResult results,Model model, RedirectAttributes ra) {
		if(results.hasErrors()) {
			return "registrarPeso";
		}
		return "redirect:/home/registrarPeso";
	}

}
