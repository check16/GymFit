package com.asanast.gymfit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asanast.gymfit.pojo.Usuario;

/**
 * Controlador del login de la aplicacion
 * @author antonio
 */
@Controller
public class LoginController {

	
	/**
	 * Metodo de bindeo de inicio para la fecha de registro.
	 * @param bindeo de datos para la fecha de registro del usuario
	 */
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaRegistro", new CustomDateEditor(dateFormat, true));
	} 
	
	/**
	 * Metodo para la peticion de login
	 * @param model el modelo de la vista
	 * @return la vista
	 */
	@RequestMapping("/login")
	public String irLogin(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

		    //El usuario esta logeado
		    return ("redirect:/home");
		}
		return "login";
	}
	
	/**
	 * Metodo para la peticion de logout de la aplicacion
	 * @param request request de la peticion
	 * @param response response de la peticion
	 * @param ra atributos de la redireccion
	 * @return la vista a mostrar
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    ra.addFlashAttribute("logout", "Saliste correctamente");
	    return "redirect:/login";
	}
	
	/**
	 * Metodo para la peticion de registro de la aplicacion
	 * @param model modelo de la vista a mostrar
	 * @return la vista
	 */
	@RequestMapping("/registro")
	public String irRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	/**
	 * Metodo para la peticion de error 403 de acceso no autorizado
	 * @return la vista de acceso no autorizado
	 */
	@RequestMapping("/403")
	public String errorAcceso() {
		return "403";
	}

}
