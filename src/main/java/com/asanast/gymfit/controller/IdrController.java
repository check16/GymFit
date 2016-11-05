package com.asanast.gymfit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IdrController {
	
	@RequestMapping("/home/idr")
	public String irIdr(Model model) {
		return "idr";
	}

}
