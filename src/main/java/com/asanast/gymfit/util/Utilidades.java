package com.asanast.gymfit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase de utilidades para la aplicacion
 * @author antonio
 */
public class Utilidades {

	/**
	 * Metodo para convertir un Date en Cadena con un determinado formato
	 * @param fecha la fecha a convertir
	 * @param formato el formato de salida de la fecha
	 * @return la fecha convertida en String
	 */
	public static String converTirFecha(Date fecha, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(fecha);
	}
	
	/**
	 * Metodo para convertir un String de fecha en un tipo Date
	 * @param fecha el String de fecha a convertir
	 * @return la fecha convertida en tipo Date
	 * @throws ParseException error al convertir la fecha proporcionada
	 */
	public static Date convertirAFecha(String fecha) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(fecha);
	}
	
}
