package com.asanast.gymfit.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Clase manejadora global para las excepciones
 * @author antonio
 */
@ControllerAdvice
public class GlobalExcepcionHandler {

	/**
	 * Metodo para manejar las excepciones y mostrar el texto con la exception
	 * @param ex la exception
	 * @return la vista de error
	 */
	@ExceptionHandler(Exception.class)
	public String handleExcepcion(Exception ex) {
		System.out.println(ex.toString());
		return "error";
	}	
}
