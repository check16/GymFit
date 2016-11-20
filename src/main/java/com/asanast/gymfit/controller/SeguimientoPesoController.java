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

@Controller
public class SeguimientoPesoController {
	
	@Autowired
	private PesoService pesoService;
	
	
	@RequestMapping("/home/seguimientoPeso")
	public String irSeguimientoPeso(Model model) {
		model.addAttribute("activo", "peso");
		model.addAttribute("activoPeso", "segPeso");
		return "seguimientoPeso";
	}
	
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
	
	@RequestMapping(value="/home/seguimientoPeso/evolucionPesoIntervalo", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Grafico obtenerPesosIntervaloDias(HttpSession sesion, @RequestParam("inicio") String inicio, String fin) throws ParseException {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Peso> pesos = pesoService.finPesoUsuarioBetweenDates(usuario, Utilidades.convertirAFecha(inicio), Utilidades.convertirAFecha(fin));
		Grafico evolucionPeso = new Grafico();
		for(Peso peso : pesos) {
			evolucionPeso.getEtiquetas().add(Utilidades.converTirFecha(peso.getFecha(), "dd/MM/yyyy"));
			evolucionPeso.getValores().add(peso.getPesoReg().toString());
		}
		return evolucionPeso;
	}
	
	@RequestMapping(value="/home/seguimientoPeso/evolucionPesoTabla", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public List<PesoTabla> obtenerPesosTabla(HttpSession sesion, @RequestParam("dias") int dias) throws ParseException {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Peso> pesos = pesoService.findAllByLastDay(usuario, dias);
		List<PesoTabla> pesosTabla = new ArrayList<PesoTabla>();
		for(Peso peso : pesos) {
			pesosTabla.add(new PesoTabla(peso.getPesoReg(), peso.getFecha()));
		}
		compruebaAumentoPerdidaPeso(pesosTabla);
		return pesosTabla;
	}
	
	private List<PesoTabla> compruebaAumentoPerdidaPeso(List<PesoTabla> pesosTabla) {
		for(int i=1; i<pesosTabla.size(); i++) {
			if(pesosTabla.get(i).getPesoValor().compareTo(pesosTabla.get(i-1).getPesoValor()) == 1) {
				pesosTabla.get(i).setEstadoPeso(EstadoPeso.AUMENTA);
			}else if(pesosTabla.get(i).getPesoValor().compareTo(pesosTabla.get(i-1).getPesoValor()) == -1) {
				pesosTabla.get(i).setEstadoPeso(EstadoPeso.DISMINUYE);
			}
			else {
				pesosTabla.get(i).setEstadoPeso(EstadoPeso.MANTIENE);
			}
		}
		pesosTabla.get(0).setEstadoPeso(EstadoPeso.MANTIENE);
		return pesosTabla;
	}
}
