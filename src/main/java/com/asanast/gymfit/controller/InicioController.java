package com.asanast.gymfit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.asanast.gymfit.service.UsuarioService;

/**
 * Controlador del Inicio de la aplicacion
 * @author antonio
 *
 */
@Controller
@SessionAttributes("usuario")
public class InicioController {
	
	/**
	 * Propiedad que encapsula el servicio del usuario
	 */
	@Autowired
	UsuarioService usuarioService;
	
	/**
	 * Metodo para la peticion de acceso raiz a la aplicacion
	 * @return
	 */
	@RequestMapping("/")
	public String acceso() {
		return "redirect:/login";
	}
	
	/**
	 * Metodo para la peticion del acceso al home de la aplicacion
	 * @return la vista a mostrar
	 */
	@RequestMapping("/home")
	public String mostrarHome() {
		return "home";
	}

}
