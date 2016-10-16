package com.asanast.gymfit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asanast.gymfit.pojo.Entrenamiento;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.EntrenamientoService;

@Controller
public class EntrenamientoController {

	@Autowired
	private EntrenamientoService entrenamientoService;

	@RequestMapping("/home/entrenamiento")
	public String irEntrenamiento(HttpSession sesion, Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		if (usuario != null) {
			List<Entrenamiento> entrenamientos = entrenamientoService.findAllByIdUsuario(usuario.getIdUsuario());
			model.addAttribute("entrenamientos", entrenamientos);
			return "entrenamiento";
		} else {
			return "redirect:/home";
		}
	}

	@RequestMapping(value = "/home/entrenamiento/{id}/eliminar", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public void eliminarEntrenamiento(@PathVariable("id") int idEntrenamiento) {
		Entrenamiento entrenamiento = entrenamientoService.findById(idEntrenamiento);
		if (entrenamiento != null) {
			entrenamientoService.delete(entrenamiento);
		}

	}

}
