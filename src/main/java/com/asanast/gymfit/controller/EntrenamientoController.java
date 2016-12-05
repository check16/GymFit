package com.asanast.gymfit.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asanast.gymfit.pojo.Ejercicio;
import com.asanast.gymfit.pojo.Entrenamiento;
import com.asanast.gymfit.pojo.TipoEjercicio;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.EjercicioService;
import com.asanast.gymfit.service.EntrenamientoService;
import com.asanast.gymfit.service.TipoEjercicioService;

/**
 * Controlador del entrenamiento
 * @author antonio
 *
 */
@Controller
public class EntrenamientoController {

	/**
	 * Propiedad que encapsula el servicio de los entrenamientos
	 */
	@Autowired
	private EntrenamientoService entrenamientoService;

	/**
	 * Propiedad que encapsula el servicio de los tipos de ejercicio
	 */
	@Autowired
	private TipoEjercicioService tipoEjercicioService;
	
	/**
	 * Propiedad que encapsula el servicio de los ejercicios
	 */
	@Autowired
	private EjercicioService ejercicioService;

	/**
	 * Metodo para la peticion de redireccion a la vista de entrenamiento
	 * @param sesion la sesion
	 * @param model el modelo para la vista
	 * @return la vista
	 */
	@RequestMapping("/home/entrenamiento")
	public String irEntrenamiento(HttpSession sesion, Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		if (usuario != null) {
			List<Entrenamiento> entrenamientos = entrenamientoService.findAllByIdUsuario(usuario.getIdUsuario());
			model.addAttribute("entrenamientos", entrenamientos);
			model.addAttribute("activo", "entrenamiento");
			return "entrenamiento";
		} else {
			return "redirect:/home";
		}
	}

	/**
	 * Metodo para la peticion que lleva a la vista de la actualizacion del entrenamiento seleccionado
	 * @param idEntrenamiento el id del entrenamiento seleccionado
	 * @param model el modelo para la vista 
	 * @return el nombre de la vista
	 */
	@RequestMapping(value = "/home/entrenamiento/{id}/actualizar")
	public String irActualizarEntrenamiento(@PathVariable("id") int idEntrenamiento, Model model) {
		Entrenamiento entrenamiento = entrenamientoService.findById(idEntrenamiento);
		if (entrenamiento != null) {
			List<TipoEjercicio> tipoEjercicios = tipoEjercicioService.findAll();
			model.addAttribute("entrenamiento", entrenamiento);
			model.addAttribute("activo", "entrenamiento");
			model.addAttribute("tipoEjercicios", tipoEjercicios);
			return "actualizarEntrenamiento";
		} else {
			return "redirect:/home/entrenamiento";
		}
	}
	
	/**
	 * Metodo para la peticion que muestra la vista para crear nuevos entrenamientos
	 * @param model el modelo de la vista
	 * @return el nombre de la vista
	 */
	@RequestMapping(value = "/home/entrenamiento/nuevoentrenamiento")
	public String irNuevoEntrenamiento(Model model) {
		model.addAttribute("tipoEjercicios", tipoEjercicioService.findAll());
		model.addAttribute("activo", "entrenamiento");
		model.addAttribute("entrenamiento", new Entrenamiento());
		return "nuevoEntrenamiento";
	}
	
	/**
	 * Metodo para le peticion de creacion de nuevos entrenamientos
	 * @param entrenamiento el entrenamiento a crear
	 * @param result el bindeo de los parametros del formulario para comprobar errores
	 * @param sesion la sesion actual
	 * @param model el modelo de la vista
	 * @param ra atributos para la redireccion
	 * @return la vista a mostrar
	 */
	@RequestMapping(value="/home/entrenamiento/crearentrenamiento")
	public String crearNuevoEntrenamiento(@Valid @ModelAttribute("entrenamiento") Entrenamiento entrenamiento,
			BindingResult result, HttpSession sesion, Model model, RedirectAttributes ra) {
		if(result.hasErrors()) {
			model.addAttribute("tipoEjercicios", tipoEjercicioService.findAll());
			return "nuevoEntrenamiento";
		}else {
			for(Ejercicio ejercicio : entrenamiento.getEjercicios()) {
				if(ejercicio.getCargaMax() == null) {
					ejercicio.setCargaMax(new BigDecimal(0));
				}
				ejercicio.setEntrenamiento(entrenamiento);
			}
			Usuario usuario = (Usuario) sesion.getAttribute("usuario");
			entrenamientoService.save(entrenamiento, usuario);
			ra.addFlashAttribute("insertado", "El entrenamiento " + entrenamiento.getNombreEntreno() + " se creó correctamente");
			return "redirect:/home/entrenamiento";
		}
		
	}

	/**
	 * Metodo para la peticion de actualizar el entrenamiento
	 * @param entrenamiento el entrenamiento a actualizar
	 * @param result el bindeo de los parametros del formulario para comprobar errores
	 * @param sesion la sesion actual
	 * @param model el modelo de la vista
	 * @param ra atributos para la redireccion
	 * @return la vista a mostrar
	 */
	@RequestMapping(value = "/home/entrenamiento/actualizar")
	public String actualizarEntrenamiento(@Valid @ModelAttribute("entrenamiento") Entrenamiento entrenamiento,
			BindingResult result, Model model, HttpSession sesion, RedirectAttributes ra) {
		if (result.hasErrors()) {
			ra.addFlashAttribute("error", "Se ha producido un error al actualizar el entrenamiento");
			model.addAttribute("tipoEjercicios", tipoEjercicioService.findAll());
			return "actualizarEntrenamiento";
		} else {
			for (Ejercicio ejercicio : entrenamiento.getEjercicios()) {
				if(ejercicio.getCargaMax() == null) {
					ejercicio.setCargaMax(new BigDecimal(0));
				}
				ejercicio.setEntrenamiento(entrenamiento);
				ejercicio.setTipoEjercicio(ejercicio.getTipoEjercicio());
			}
		}
		ra.addFlashAttribute("actualizado",
				"El entrenamiento " + entrenamiento.getNombreEntreno() + " se actualizó correctamente");
		model.addAttribute("activo", "entrenamiento");
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		entrenamiento.setUsuario(usuario);
		entrenamientoService.update(entrenamiento);
		return "redirect:/home/entrenamiento/" + entrenamiento.getIdEntrenamiento() + "/actualizar";
	}

	/**
	 * Metodo para la peticion ajax de eliminar un entrenamiento
	 * @param idEntrenamiento el id del entrenamiento a eliminar
	 * @return si se elimina o no el entrenamiento
	 */
	@RequestMapping(value = "/home/entrenamiento/{id}/eliminar", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public boolean eliminarEntrenamiento(@PathVariable("id") int idEntrenamiento) {
		Entrenamiento entrenamiento = entrenamientoService.findById(idEntrenamiento);

		if (entrenamiento != null) {
			entrenamientoService.delete(entrenamiento);
			return true;
		}
		return false;

	}
	
	/**
	 * Metodo para la peticion ajax de eliminar un ejercicio del entrenamiento
	 * @param idEjercicio el id del ejercicio a eliminar
	 * @return si se elimina o no el ejercicio
	 */
	@RequestMapping(value = "/home/entrenamiento/ejercicio/{id}/eliminar", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public boolean eliminarEjercicio(@PathVariable("id") int idEjercicio) {
		Ejercicio ejercicio = ejercicioService.findByIdEjercicio(idEjercicio);

		if (ejercicio != null) {
			ejercicioService.delete(ejercicio);
			return true;
		}else {
			return false;
		}

	}

	/**
	 * Metodo para la peticion ajax de crear ejercicio el cual proporciona la lista con los ejercicios
	 * @return ejercicios la lista de ejercicios
	 */
	@RequestMapping(value = "/home/entrenamiento/crearejercicio", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public List<TipoEjercicio> dameEjercicios() {
		List<TipoEjercicio> ejercicios = tipoEjercicioService.findAll();
		return ejercicios;
	}

}
