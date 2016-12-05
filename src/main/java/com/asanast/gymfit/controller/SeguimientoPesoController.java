package com.asanast.gymfit.controller;

import java.text.ParseException;
import java.util.ArrayList;
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

import com.asanast.gymfit.pojo.Grafico;
import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.pojo.VO.PesoTabla;
import com.asanast.gymfit.pojo.VO.PesoTabla.EstadoPeso;
import com.asanast.gymfit.service.PesoService;
import com.asanast.gymfit.util.Utilidades;

/**
 * Controlador para el seguimiento del peso
 * @author antonio
 */
@Controller
public class SeguimientoPesoController {
	
	/**
	 * Propiedad que encapsula el servicio del peso
	 */
	@Autowired
	private PesoService pesoService;
	
	
	/**
	 * Metodo para la peticion de ir a la vista del seguimiento del peso
	 * @param model el modelo de la vista
	 * @return la vista
	 */
	@RequestMapping("/home/seguimientoPeso")
	public String irSeguimientoPeso(Model model) {
		model.addAttribute("activo", "peso");
		model.addAttribute("activoPeso", "segPeso");
		return "seguimientoPeso";
	}
	
	/**
	 * Metodo para la petición ajax de obtener la evolucion del peso X dias atrás del día actual.
	 * @param sesion la sesion
	 * @param dias numero de dias atrás sobre el día actual para el que se quiere conocer la evolucion del peso
	 * @return evolucionPeso el grafico con los datos para la evolucion del peso
	 */
	@RequestMapping(value="/home/seguimientoPeso/evolucionPeso", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Grafico obtenerPesos(HttpSession sesion, @RequestParam("dias") int dias) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Peso> pesos = pesoService.findAllByLastDay(usuario, dias);
		Grafico evolucionPeso = new Grafico();
		for(Peso peso : pesos) {
			evolucionPeso.getEtiquetas().add(Utilidades.converTirFecha(peso.getFecha(), "dd/MM/yyyy"));
			evolucionPeso.getValores().add(peso.getPesoReg().toString());
		}
		return evolucionPeso;
	}
	
	/**
	 * Metodo para la peticion ajax de evolucion del peso en un intervalo de tiempo
	 * @param sesion la sesion
	 * @param inicio fecha de inicio para conocer la evolucion del peso
	 * @param fin fehca de fin para la evolucion del peso
	 * @return evolucionPeso grafico con los datos de la evolución del peso
	 * @throws ParseException excepcion de parseo de la fecha
	 */
	@RequestMapping(value="/home/seguimientoPeso/evolucionPesoIntervalo", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Grafico obtenerPesosIntervaloDias(HttpSession sesion, @RequestParam("inicio") String inicio, String fin) throws ParseException {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Peso> pesos = pesoService.findPesoUsuarioBetweenDates(usuario, Utilidades.convertirAFecha(inicio), Utilidades.convertirAFecha(fin));
		Grafico evolucionPeso = new Grafico();
		for(Peso peso : pesos) {
			evolucionPeso.getEtiquetas().add(Utilidades.converTirFecha(peso.getFecha(), "dd/MM/yyyy"));
			evolucionPeso.getValores().add(peso.getPesoReg().toString());
		}
		return evolucionPeso;
	}
	
	/**
	 * Metodo para la peticion ajax de evolucion del peso en formato tabla X dias atrás del día actual.
	 * @param sesion la sesion
	 * @param dias numero de dias atrás sobre el día actual para el que se quiere conocer la evolucion del peso
	 * @return pesosTabla los datos para mostrar la tabla con los pesos
	 * @throws ParseException excepcion de parseo de la fecha 
	 */
	@RequestMapping(value="/home/seguimientoPeso/evolucionPesoTabla", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public List<PesoTabla> obtenerTablaPesosTabla(HttpSession sesion, @RequestParam("dias") int dias) throws ParseException {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Peso> pesos = pesoService.findAllByLastDay(usuario, dias);
		List<PesoTabla> pesosTabla = new ArrayList<PesoTabla>();
		for(Peso peso : pesos) {
			pesosTabla.add(new PesoTabla(peso.getPesoReg(), peso.getFecha()));
		}
		compruebaAumentoPerdidaPeso(pesosTabla);
		return pesosTabla;
	}
	
	/**
	 * Metodo para la peticion ajax de evolucion del peso en formato tabla en un intervalo de tiempo
	 * @param sesion la sesion
	 * @param inicio fecha de inicio del intervalo de tiempo para la evolucion del peso
	 * @param fin fecha de fin del intervalo de tiempo para la evolucion del peso
	 * @return pesosTabla objeto que encapsula los datos para mostrar en la tabla referente a los pesos
	 * @throws ParseException excepcion de parseo de la fecha 
	 */
	@RequestMapping(value="/home/seguimientoPeso/evolucionPesoIntervaloTabla", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public List<PesoTabla> obtenerTablaPesosIntervaloDias(HttpSession sesion, @RequestParam("inicio") String inicio, String fin) throws ParseException {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Peso> pesos = pesoService.findPesoUsuarioBetweenDates(usuario, Utilidades.convertirAFecha(inicio), Utilidades.convertirAFecha(fin));
		List<PesoTabla> pesosTabla = new ArrayList<PesoTabla>();
		for(Peso peso : pesos) {
			pesosTabla.add(new PesoTabla(peso.getPesoReg(), peso.getFecha()));
		}
		if(pesosTabla.size() > 0)
			compruebaAumentoPerdidaPeso(pesosTabla);
		return pesosTabla;
	}
	
	/**
	 * Metodo que encapsula el sistema para determinar si un usuario aumenta, disminuye o mantiene el peso
	 * @param pesosTabla los datos de la tabla con el peso para mostrar
	 * @return pesosTabla los datos de la tabla con el estado del peso actualizado
	 */
	private List<PesoTabla> compruebaAumentoPerdidaPeso(List<PesoTabla> pesosTabla) {
		if(!pesosTabla.isEmpty()) {
			for(int i=1; i<pesosTabla.size(); i++) {
				if(pesosTabla.get(i).getPesoValor().compareTo(pesosTabla.get(i-1).getPesoValor()) == 1) {
					pesosTabla.get(i).setEstadoPeso(EstadoPeso.AUMENTA);
					pesosTabla.get(i).setIconoEstado("<button type='button' class='btn btn-danger'>Aumenta <i class='fa fa-arrow-circle-o-up'></i></button></td></tr>");
				}else if(pesosTabla.get(i).getPesoValor().compareTo(pesosTabla.get(i-1).getPesoValor()) == -1) {
					pesosTabla.get(i).setEstadoPeso(EstadoPeso.DISMINUYE);
					pesosTabla.get(i).setIconoEstado("<button type='button' class='btn btn-success'>Disminuye <i class='fa fa-arrow-circle-o-down'></i></button></td></tr>");
				}
				else {
					pesosTabla.get(i).setEstadoPeso(EstadoPeso.MANTIENE);
					pesosTabla.get(i).setIconoEstado("<button type='button' class='btn btn-warning'>Mantiene <i class='fa fa-minus'></i></button></td></tr>");
				}
			}
			pesosTabla.get(0).setEstadoPeso(EstadoPeso.MANTIENE);
			pesosTabla.get(0).setIconoEstado("<button type='button' class='btn btn-warning'>Mantiene <i class='fa fa-minus'></i></button></td></tr>");
			return pesosTabla;
		}
		return pesosTabla;
		
	}
}
