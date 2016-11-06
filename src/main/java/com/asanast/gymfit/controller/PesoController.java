package com.asanast.gymfit.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
			model.addAttribute("activo", "peso");
			model.addAttribute("ultimoPeso", pesoService.findLastPeso(usuario));
			model.addAttribute("peso", new Peso());
		}else {
			model.addAttribute("activo", "peso");
			model.addAttribute("ultimoPeso", new Peso());
			model.addAttribute("peso", new Peso());
		}
	}
	
	@RequestMapping("/home/registrarPeso")
	public String irRegistrarPeso(Model model) {
		model.addAttribute("activoPeso", "regPeso");
		model.addAttribute("activo", "peso");
		return "registrarPeso";
	}
	
	@RequestMapping("/home/registrarPeso/registrar")
	public String registrarPeso(@Valid @ModelAttribute ("peso") Peso peso, BindingResult results,Model model, HttpSession sesion) {
		if(results.hasErrors()) {
			
			return "registrarPeso";
		}else {
			Usuario usuario = (Usuario) sesion.getAttribute("usuario");
			peso.setUsuario(usuario);
			pesoService.saveOrUpdate(peso);
			return "redirect:/home/registrarPeso";
		}
		
	}

}
