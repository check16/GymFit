package com.asanast.gymfit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asanast.gymfit.service.TipoEjercicioService;

/**
 * Controlador referente a los tipos de Ejercicio.
 * @author antonio
 */
@Controller
public class EjerciciosController {
	
	/**
	 * Propiedad que encapsula el servicio para los tipos de ejercicio
	 */
	@Autowired
	private TipoEjercicioService tipoEjercicioService;
	
	/**
	 * Metodo que redirige a la vista para visualizar los ejercicios.
	 * @param model el modelo de la vista
	 * @return la vista a mostrar
	 */
	@RequestMapping("/home/aprendeEjercicios")
	public String irAprenderEjercicios(Model model) {
		model.addAttribute("activo", "ejercicios");
		model.addAttribute("ejercicios", tipoEjercicioService.findAll());
		return "aprendeEjercicios";
	}

}
