package com.asanast.gymfit.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcepcionHandler {

	@ExceptionHandler(Exception.class)
	public String handleExcepcion(Exception ex) {
		System.out.println(ex.toString());
		return "error";
	}	
}
