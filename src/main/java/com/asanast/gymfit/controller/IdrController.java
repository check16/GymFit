package com.asanast.gymfit.controller;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String calcularIdr(@Valid @ModelAttribute("idrForm") IdrForm idrForm, BindingResult results, Model model) {
		if(results.hasErrors()) {
			return "idr";
		}else {
			//TODO Crear calculo de IDR
			return "redirect:/home/idr";
		}
	}

}
