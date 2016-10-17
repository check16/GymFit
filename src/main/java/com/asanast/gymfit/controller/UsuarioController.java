package com.asanast.gymfit.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
		
        dataBinder.setDisallowedFields("fechaRegistro");
    }
	
	@RequestMapping("/registrarse")
	public String registrarse(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes ra) {
		if(result.hasErrors()) {
			ra.addFlashAttribute("error", true);
			ra.addFlashAttribute("mensaje", "Error al registrarse!");
			return "registro";
		}
		
		usuario.setFechaRegistro(new Date());
		usuarioService.save(usuario);
		ra.addFlashAttribute("error", false);
		ra.addFlashAttribute("mensaje", "Registrado correctamente!");
		return "redirect:registro";
	}

}
