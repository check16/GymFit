package com.asanast.gymfit.pojo.VO;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase Value Object para la representacion de un Grafico
 * @author antonio
 */
public class Grafico {

	/**
	 * Propiedad que encapsula el conjunto de etiquetas del grafico
	 */
	private List<String> etiquetas;
	
	/**
	 * Propiedades que encapsula el conjunto de valores
	 */
	private List<String> valores;
	
	/**
	 * Propiedad que encapsula el conjunto de segundos valores en caso de necesitarse
	 */
	private List<String> valores2;

	public Grafico() {
		etiquetas = new ArrayList<String>();
		valores = new ArrayList<String>();
		valores2 = new ArrayList<String>();
	}

	/**
	 * Metodo getter de las etiquetas
	 * @return el conjunto de etiquetas del grafico
	 */
	public List<String> getEtiquetas() {
		return etiquetas;
	}

	/**
	 * Metod setter de las etiquetas
	 * @param etiquetas el conjunto de etiquetas
	 */
	public void setEtiquetas(List<String> etiquetas) {
		this.etiquetas = etiquetas;
	}

	/**
	 * Metodo getter de los valores del grafico
	 * @return el conjunto de valores del grafico
	 */
	public List<String> getValores() {
		return valores;
	}

	/**
	 * Metodo setter de los valores del grafico
	 * @param valores los valores del grafico
	 */
	public void setValores(List<String> valores) {
		this.valores = valores;
	}

	/**
	 * Metodo getter de los segundos valores del grafico
	 * @return los segundos valores del grafico
	 */
	public List<String> getValores2() {
		return valores2;
	}

	/**
	 * Metodo setter de los segundos valores del grafico
	 * @param valores2 los segundos valores del grafico
	 */
	public void setValores2(List<String> valores2) {
		this.valores2 = valores2;
	}
	
	
}
