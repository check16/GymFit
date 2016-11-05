package com.asanast.gymfit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.pojo.VO.PasswordForm;
import com.asanast.gymfit.service.UsuarioService;
import com.asanast.gymfit.validations.PasswordFormValidator;

@Controller

public class UsuarioController {
	
	@Autowired
    PasswordFormValidator passwordValidator;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder bcpe;
	
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
		model.addAttribute("passwordForm", new PasswordForm());
		return "perfil";
	}
	
	@RequestMapping(value="/home/perfil/actualizar", method = RequestMethod.POST)
	public String actualizarPerfil(@Valid @ModelAttribute("usuario") Usuario perfilForm,BindingResult result, @ModelAttribute("passwordForm") PasswordForm pf, RedirectAttributes ra, HttpSession sesion, Model model) {
		if(result.hasErrors()) {
			return "perfil";
		}else {
			Usuario usuario = usuarioService.findById(perfilForm.getIdUsuario());
			usuario.setNombreApellido(perfilForm.getNombreApellido());
			usuario.setAltura(perfilForm.getAltura());
			usuario.setEdad(perfilForm.getEdad());
			usuarioService.update(usuario);
			ra.addFlashAttribute("actualizado", "Los datos personales se modificaron correctamente.");
			sesion.setAttribute("usuario", usuario);
			return "redirect:/home/perfil";
		}
	}
	
 	@RequestMapping(value="/home/perfil/modificarclave", method = RequestMethod.POST)
	public List<ObjectError> modificarClave(@Valid @ModelAttribute("passwordForm") PasswordForm pf,HttpSession sesion, BindingResult results) {
 		passwordValidator.validate(pf, results);
 		if(results.hasErrors()) {
 			return results.getAllErrors();
 		}else {
 			Usuario usuario = (Usuario) sesion.getAttribute("usuario");
 			usuario.setClave(bcpe.encode(pf.getClave()));
 			usuarioService.update(usuario);
 			return results.getAllErrors();
 		}
 	}
	

}
