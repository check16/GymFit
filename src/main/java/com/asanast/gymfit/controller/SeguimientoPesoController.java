package com.asanast.gymfit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.pojo.VO.EvolucionPeso;
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
	public EvolucionPeso obtenerPesos(HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Peso> pesos = pesoService.findAllByIdUsuario(usuario);
		EvolucionPeso evolucionPeso = new EvolucionPeso();
		for(Peso peso : pesos) {
			evolucionPeso.getFechas().add(Utilidades.converTirFecha(peso.getFecha(), "dd/MM/yyyy"));
			evolucionPeso.getPesos().add(peso.getPesoReg());
		}
		return evolucionPeso;
	}
}
