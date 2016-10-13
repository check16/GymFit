package com.asanast.gymfit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asanast.gymfit.pojo.Usuario;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String irLogin() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    ra.addFlashAttribute("logout", "Saliste correctamente");
	    return "redirect:/login";
	}
	
	@RequestMapping("/registro")
	public String irRegistro(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "registro";
	}
	
	@RequestMapping("/403")
	public String errorAcceso() {
		return "403";
	}

}
