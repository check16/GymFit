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

/**
 * Clase controladora para el IDR
 * @author antonio
 */
@Controller
public class IdrController {
	
	/**
	 * Propiedad que encapsula el servicio para el peso
	 */
	@Autowired
	private PesoService pesoService;
	
	/**
	 * Metodo para la peticion de mostrar el idr
	 * @param model el modelo para la vista
	 * @param sesion la sesion
	 * @return la vista a mostrar
	 */
	@RequestMapping("/home/idr")
	public String irIdr(Model model, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		if(usuario != null) {
		IdrForm idrForm = new IdrForm();
		idrForm.setAltura(usuario.getAltura());
		idrForm.setEdad(usuario.getEdad());
		Peso peso = pesoService.findLastPeso(usuario);
		if(peso != null)
			idrForm.setPeso(peso.getPesoReg());
		model.addAttribute("idrForm", idrForm);
		model.addAttribute("activo", "idr");
		}else {
			model.addAttribute("idrForm" , new IdrForm());
			model.addAttribute("activo", "idr");
		}
		
		return "idr";
	}
	
	/**
	 * Metodo para la peticion de calcular el idr
	 * @param idrForm los datos del formulario del idr
	 * @param results los datos bindeados del formulario de idr
	 * @param ra los atributos para la redireccion
	 * @return la vista a mostrar
	 */
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

	/**
	 * Metodo que encapsula el calculo de la tasa metabolica basal a partir de los datos del formulario
	 * @param datosForm los datos del formulario
	 * @return tmb la tasa metabolica basal
	 */
	private double calcularTasaMetabolicaBasal(IdrForm datosForm) {
		if(datosForm.getSexo().equals(IdrForm.Sexo.M)) {
			double tmb = (10 * datosForm.getPeso().floatValue()) + (6.25 * datosForm.getAltura()) - (5 * datosForm.getEdad()) + 5;
			return tmb;
		}else {
			double tmb = (10 * datosForm.getPeso().floatValue()) + (6.25 * datosForm.getAltura()) - (5 * datosForm.getEdad()) - 161;
			return tmb;
		}
	}
	
	/**
	 * Metodo que encapsula el calculo de la tasa metabolica basal asociando el nivel de actidad
	 * @param tmb la tasa metabolica basal
	 * @param datosForm los datos del formulario
	 * @return tmb la tasa metabolica basal con el nivel de actividad asociado
	 */
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
	
	/**
	 * Metodo que encapsula el calculo de la tasa metabolica basal asociando el objetivo
	 * @param tmbConActividad la tasa metabolica basal con el nivel de actividad
	 * @param datosForm los datos del formulario
	 * @return tmbConActividad la tasa metabolica basal con la actividad y el objetivo
	 */
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
	
	/**
	 * Metodo que encapsula el calculo del porcentaje de incremento sobre un valor de 2000Kcal
	 * @param tmbConActividadObjetivo la tasa metabolica basal con el nivel de actividad y el objetivo
	 * @return el valor del porcentaje de incremento
	 */
	private double calcularPorcIncremento(double tmbConActividadObjetivo) {
		return ((tmbConActividadObjetivo*100)/2000) - 100;
	}

}
