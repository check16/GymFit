package com.asanast.gymfit.pojo;

import java.util.ArrayList;
import java.util.List;

public class Grafico {

	private List<String> etiquetas;
	private List<String> valores;

	public Grafico() {
		etiquetas = new ArrayList<String>();
		valores = new ArrayList<String>();
	}

	public List<String> getFechas() {
		return etiquetas;
	}

	public void setFechas(List<String> fechas) {
		this.etiquetas = fechas;
	}

	public List<String> getPesos() {
		return valores;
	}

	public void setPesos(List<String> pesos) {
		this.valores = pesos;
	}
	
	

}
