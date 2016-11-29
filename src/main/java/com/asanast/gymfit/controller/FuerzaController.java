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
import com.asanast.gymfit.pojo.Grafico;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.EntrenamientoService;
import com.asanast.gymfit.service.TipoEjercicioService;
import com.asanast.gymfit.util.Utilidades;

@Controller
public class FuerzaController {
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@Autowired
	private TipoEjercicioService tipoEjercicioService;
	
	@RequestMapping("/home/seguimientoFuerza")
	public String irSeguimientoFuerza(Model model) {
		model.addAttribute("activo", "fuerza");
		model.addAttribute("ejercicios", tipoEjercicioService.findAll());
		return "seguimientoFuerza";
	}
	
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
