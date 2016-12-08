package com.asanast.gymfit.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asanast.gymfit.pojo.Ejercicio;
import com.asanast.gymfit.pojo.Entrenamiento;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.pojo.VO.Grafico;
import com.asanast.gymfit.service.EntrenamientoService;
import com.asanast.gymfit.service.TipoEjercicioService;
import com.asanast.gymfit.util.Utilidades;

/**
 * Controlador para la gestion de la Fuerza en la aplicacion
 * @author antonio
 */
@Controller
public class FuerzaController {
	
	/**
	 * Propiedad que encapsula el servicio del Entrenamiento
	 */
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	/**
	 * Propiedad que encapsula el servicio de los tipos de ejercicio
	 */
	@Autowired
	private TipoEjercicioService tipoEjercicioService;
	
	/**
	 * Metodo para la peticion de mostrar el seguimiento de la fuerza
	 * @param model el modelo de la vista
	 * @return la vista a mostrar
	 */
	@RequestMapping("/home/seguimientoFuerza")
	public String irSeguimientoFuerza(Model model) {
		model.addAttribute("activo", "fuerza");
		model.addAttribute("ejercicios", tipoEjercicioService.findAll());
		return "seguimientoFuerza";
	}
	
	/**
	 * Metodo para la peticion ajax de mostrar la evolucion de la fuerza por intervalo de tiempo
	 * @param sesion la sesion
	 * @param inicio fecha de inicio de la evolucion de la fuerza
	 * @param fin fecha de fin de la evolucion de la fuerza
	 * @param idEjercicio el id del ejercicio a mostrar la evolución
	 * @return evolucionFuerza Grafico con la evolucion de la fuerza
	 * @throws ParseException excepcion de conversion de la fecha
	 */
	@RequestMapping(value="/home/seguimientoPeso/evolucionFuerzaIntervalo", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Grafico obtenerPesosIntervaloDias(HttpSession sesion, @RequestParam("inicio") String inicio, String fin, int idEjercicio) throws ParseException {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Entrenamiento> entrenamientos = entrenamientoService.findFuerzaEjercicioUsuarioBetweenDates(usuario, Utilidades.convertirAFecha(inicio), Utilidades.convertirAFecha(fin), idEjercicio);
		Grafico evolucionFuerza = new Grafico();
		for(Entrenamiento entrenamiento : entrenamientos) {
			for(Ejercicio ejercicio : entrenamiento.getEjercicios()) {
				evolucionFuerza.getEtiquetas().add(Utilidades.converTirFecha(entrenamiento.getFecha(), "dd/MM/yyyy"));
				evolucionFuerza.getValores().add(ejercicio.getCargaMax().toString());
				evolucionFuerza.getValores2().add(String.valueOf(ejercicio.getTotalRepeticiones()));
			}
		}
		return evolucionFuerza;
	}

}
