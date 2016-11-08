package com.asanast.gymfit.controller;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.pojo.VO.IdrForm;
import com.asanast.gymfit.service.PesoService;
import com.asanast.gymfit.service.UsuarioService;

@Controller
public class IdrController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PesoService pesoService;
	
	@RequestMapping("/home/idr")
	public String irIdr(Model model, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		Usuario usuarioForm = usuarioService.findById(usuario.getIdUsuario());
		if(usuarioForm != null) {
		IdrForm idrForm = new IdrForm();
		idrForm.setAltura(usuarioForm.getAltura());
		idrForm.setEdad(usuarioForm.getEdad());
		Peso peso = pesoService.findLastPeso(usuarioForm);
		idrForm.setPeso(peso.getPesoReg());
		model.addAttribute("idrForm", idrForm);
		model.addAttribute("activo", "idr");
		}else {
			model.addAttribute("idrForm" , new IdrForm());
		}
		
		return "idr";
	}
	
	@RequestMapping("/home/idr/calcularIdr")
	public String calcularIdr(@Valid @ModelAttribute("idrForm") IdrForm idrForm, BindingResult results, RedirectAttributes ra) {
		if(results.hasErrors()) {
			return "idr";
		}else {
			double tasaMetabolicaBasal = calcularTasaMetabolicaBasal(idrForm);
			double tmbConActividad = calcularTmbConActividad(tasaMetabolicaBasal, idrForm);
			Double tmbConActividadObjetivo = calcularTmbActividadObjetivo(tmbConActividad, idrForm);
			Double porcIncremento = calcularPorcIncremento(tmbConActividadObjetivo);
			ra.addFlashAttribute("kcalNecesarias", tmbConActividadObjetivo.intValue());
			ra.addFlashAttribute("porcIncremento", porcIncremento.intValue());
			return "redirect:/home/idr";
		}
	}

	private double calcularTasaMetabolicaBasal(IdrForm datosForm) {
		if(datosForm.getSexo().equals(IdrForm.Sexo.M)) {
			double tmb = (10 * datosForm.getPeso().floatValue()) + (6.25 * datosForm.getAltura()) - (5 * datosForm.getEdad()) + 5;
			return tmb;
		}else {
			double tmb = (10 * datosForm.getPeso().floatValue()) + (6.25 * datosForm.getAltura()) - (5 * datosForm.getEdad()) - 161;
			return tmb;
		}
	}
	
	private double calcularTmbConActividad(double tmb, IdrForm datosForm) {
		if(datosForm.getActividad().equals(IdrForm.Actividad.SEDENTARIO)) {
			return tmb * 1.2;
		}else if(datosForm.getActividad().equals(IdrForm.Actividad.LIGERA)) {
			return tmb * 1.375;
		}else if(datosForm.getActividad().equals(IdrForm.Actividad.MODERADA)) {
			return tmb * 1.55;
		}else if(datosForm.getActividad().equals(IdrForm.Actividad.INTENSA)) {
			return tmb * 1.725;
		}else if(datosForm.getActividad().equals(IdrForm.Actividad.MUYINTENSA)) {
			return tmb * 1.9;
		}	
		return tmb * 1.55;
	}
	
	private double calcularTmbActividadObjetivo(double tmbConActividad, IdrForm datosForm) {
		if(datosForm.getObjetivo().equals(IdrForm.Objetivo.PERDER_LENTAMENTE)) {
			return tmbConActividad - 250;
		}else if(datosForm.getObjetivo().equals(IdrForm.Objetivo.PERDER)) {
			return tmbConActividad - 500;
		}else if(datosForm.getObjetivo().equals(IdrForm.Objetivo.AUMENTAR_LENTAMENTE)) {
			return tmbConActividad + 250;
		}else if(datosForm.getObjetivo().equals(IdrForm.Objetivo.AUMENTAR)) {
			return tmbConActividad + 500;
		}else if(datosForm.getObjetivo().equals(IdrForm.Objetivo.MANTENER)) {
			return tmbConActividad;
		}
		return tmbConActividad;
	}
	
	private double calcularPorcIncremento(double tmbConActividadObjetivo) {
		return ((tmbConActividadObjetivo*100)/2000) - 100;
	}

}
