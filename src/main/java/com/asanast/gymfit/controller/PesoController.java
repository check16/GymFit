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

/**
 * Controlador para el Peso
 * @author antonio
 *
 */
@Controller
public class PesoController {
	
	/**
	 * Propiedad que encapsula el servicio del peso
	 */
	@Autowired
	private PesoService pesoService;
	
	/**
	 * Metodo de inicialización  del controlador para el peso
	 * @param model el modelo para la vista
	 * @param sesion la sesion
	 */
	@ModelAttribute
	public void init(Model model, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		Peso ultimoPeso = pesoService.findLastPeso(usuario);
		if(ultimoPeso != null) {
			model.addAttribute("activo", "peso");
			model.addAttribute("ultimoPeso", pesoService.findLastPeso(usuario));
			model.addAttribute("promedioPeso", pesoService.avgPesoFromLastDias(usuario, 30));
			model.addAttribute("peso", new Peso());
		}else {
			model.addAttribute("activo", "peso");
			model.addAttribute("ultimoPeso", new Peso());
			model.addAttribute("peso", new Peso());
		}
	}
	
	/**
	 * Metodo para la peticion de ir a la vista de registro del peso
	 * @param model el modelo de la vista
	 * @return la vista
	 */
	@RequestMapping("/home/registrarPeso")
	public String irRegistrarPeso(Model model) {
		model.addAttribute("activoPeso", "regPeso");
		model.addAttribute("activo", "peso");
		return "registrarPeso";
	}
	
	/**
	 * Metodo para la peticion de registrar un peso 
	 * @param peso el peso a registrar
	 * @param results encapsula el bindeo del formulario del peso
	 * @param model el modelo de la vista
	 * @param sesion la sesion
	 * @param ra atributos para la redirección
	 * @return la vista
	 */
	@RequestMapping("/home/registrarPeso/registrar")
	public String registrarPeso(@Valid @ModelAttribute ("peso") Peso peso, BindingResult results,Model model, HttpSession sesion, RedirectAttributes ra) {
		if(results.hasErrors()) {
			
			return "registrarPeso";
		}else {
			Usuario usuario = (Usuario) sesion.getAttribute("usuario");
			peso.setUsuario(usuario);
			pesoService.saveOrUpdate(peso);
			ra.addFlashAttribute("registrado", "El peso se registró correctamente");
			return "redirect:/home/registrarPeso";
		}
	}

}
