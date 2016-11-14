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

	public List<String> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<String> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public List<String> getValores() {
		return valores;
	}

	public void setValores(List<String> valores) {
		this.valores = valores;
	}
}
