package com.asanast.gymfit.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.UsuarioService;

@Controller
@SessionAttributes(value = "usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/registrarse")
	public String registrarse(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes ra) {
		if(result.hasErrors()) {
			ra.addFlashAttribute("error", true);
			ra.addFlashAttribute("mensaje", "Error al registrarse!");
			return "registro";
		}
		usuario.setFechaRegistro(new Date());
		usuario.setRutaFoto("user-icon.png");
		usuarioService.save(usuario);
		ra.addFlashAttribute("error", false);
		ra.addFlashAttribute("mensaje", "Registrado correctamente!");
		return "redirect:registro";
	}
	
	@RequestMapping(value="/home/perfil")
	public String irPerfil(Model model, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		
		model.addAttribute("activo", "perfil");
		model.addAttribute("usuario", usuarioService.findById(usuario.getIdUsuario()));
		return "perfil";
	}
	
	@RequestMapping(value="/home/perfil/actualizar")
	public String actualizarPerfil(@ModelAttribute("usuario") @Valid Usuario perfilForm,BindingResult result,RedirectAttributes ra, Model model) {
		if(result.hasErrors()) {
			return "perfil";
		}else {
			Usuario usuario = usuarioService.findById(perfilForm.getIdUsuario());
			usuario.setNombreApellido(perfilForm.getNombreApellido());
			usuario.setAltura(perfilForm.getAltura());
			usuario.setEdad(perfilForm.getEdad());
			usuarioService.update(usuario);
			ra.addFlashAttribute("actualizado", "Los datos personales se modificaron correctamente.");
			model.addAttribute("activo", "perfil");
			model.addAttribute("usuario", usuario);
			return "redirect:/home/perfil";
		}
	}

}
