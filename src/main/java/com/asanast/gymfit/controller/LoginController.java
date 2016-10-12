package com.asanast.gymfit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String irLogin() {
		return "login";
	}
	
	@RequestMapping("/403")
	public String errorAcceso() {
		return "403";
	}

}
