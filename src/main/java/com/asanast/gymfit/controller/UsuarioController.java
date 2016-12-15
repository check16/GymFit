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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.pojo.VO.PasswordForm;
import com.asanast.gymfit.service.UsuarioService;
import com.asanast.gymfit.validations.PasswordFormValidator;

/**
 * Controlador para el usuario
 * @author antonio
 */
@Controller
public class UsuarioController {
	
	/**
	 * Propiedad que encapsula el validador del formulario de cambio de contraseña
	 */
	@Autowired
    private PasswordFormValidator passwordValidator;
	
	/**
	 * Propiedad que encapsula el servicio de usuario
	 */
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * Propiedad que encapsula el encoder para la contraseña
	 */
	@Autowired
	private BCryptPasswordEncoder bcpe;
	
	/**
	 * Metodo para la peticion de registro de usuario
	 * @param usuario el usuario a registrar
	 * @param result el bindeo del formulario de registro
	 * @param ra atributos para la redireccion
	 * @return la vista
	 */
	@RequestMapping("/registrarse")
	public String registrarse(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result, RedirectAttributes ra) {
		if(result.hasErrors()) {
			ra.addFlashAttribute("error", true);
			ra.addFlashAttribute("mensaje", "Compruebe los campos");
			return "registro";
		}else if(usuarioService.existsUsuario(usuario)) {
			ra.addFlashAttribute("error", true);
			ra.addFlashAttribute("mensaje", "El login o email ya existe!");
			return "redirect:registro";
		}
		usuario.setFechaRegistro(new Date());
		usuario.setRutaFoto("user-icon.png");
		usuarioService.save(usuario);
		ra.addFlashAttribute("error", false);
		ra.addFlashAttribute("mensaje", "Registrado correctamente!");
		return "redirect:registro";
	}
	
	/**
	 * Metodo para la petición de ir al perfil de usuario
	 * @param model el modelo de la vista
	 * @param sesion la sesion
	 * @return la vista
	 */
	@RequestMapping(value="/home/perfil")
	public String irPerfil(Model model, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		
		model.addAttribute("activo", "perfil");
		model.addAttribute("usuario", usuarioService.findById(usuario.getIdUsuario()));
		model.addAttribute("passwordForm", new PasswordForm());
		return "perfil";
	}
	
	/**
	 * Metodo para la peticion de actualizar datos del perfil de usuario
	 * @param perfilForm el formulario del perfil de usuario
	 * @param result el bindeo del formulario del perfil
	 * @param ra atributos para la redireccion
	 * @param sesion la sesion
	 * @param model el modelo para la vista
	 * @return la vista
	 */
	@RequestMapping(value="/home/perfil/actualizar", method = RequestMethod.POST)
	public String actualizarPerfil(@Valid @ModelAttribute("usuario") Usuario perfilForm,BindingResult result, RedirectAttributes ra, HttpSession sesion, Model model) {
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
	
	/**
	 * Metodo para la peticion ajax de modificacion de la contraseña de usuario
	 * @param pf el formulario con los datos del cambio de contraseña
	 * @param sesion la sesion
	 * @param results el bindeo del formulario del cambio de contraseña
	 * @return los errores del bindeo del cambio de contraseña
	 */
 	@RequestMapping(value="/home/perfil/modificarclave", method = RequestMethod.POST)
 	@ResponseBody
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
