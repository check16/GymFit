package com.asanast.gymfit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades {

	public static String converTirFecha(Date fecha, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(fecha);
	}
}
