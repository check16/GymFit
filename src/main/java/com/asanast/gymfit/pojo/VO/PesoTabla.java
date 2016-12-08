package com.asanast.gymfit.pojo.VO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase Value Object para los datos de la tabla del seguimiento del peso
 * @author antonio
 */
public class PesoTabla {

	/**
	 * Propiedad que encapsula el valor del peso
	 */
	private BigDecimal pesoValor;
	
	/**
	 * Propiedad que encapsula la fecha del peso
	 */
	private Date fechaPeso;
	
	/**
	 * Propiedad que encapsula el estado del peso
	 */
	private EstadoPeso estadoPeso;
	
	/**
	 * Propiedad que encapsula el icono del estado del peso
	 */
	private String iconoEstado;

	/**
	 * Constructor por defecto de la clase
	 */
	public PesoTabla() {

	}
	
	/**
	 * Constructor con parametros de la clase
	 * @param pesoValor valor del peso
	 * @param fechaPeso fecha del peso
	 */
	public PesoTabla(BigDecimal pesoValor, Date fechaPeso) {
		this.pesoValor = pesoValor;
		this.fechaPeso = fechaPeso;
		
	}

	/**
	 * Constructor con parametros de la clase
	 * @param pesoValor valor del peso
	 * @param fechaPeso fecha del peso
	 * @param iconoEstado icono del estado del peso
	 */
	public PesoTabla(BigDecimal pesoValor, Date fechaPeso, String iconoEstado) {
		this.pesoValor = pesoValor;
		this.fechaPeso = fechaPeso;
		this.iconoEstado = iconoEstado;
		
	}

	/**
	 * Metodo getter del valor del peso
	 * @return el valor del peso
	 */
	public BigDecimal getPesoValor() {
		return pesoValor;
	}

	/**
	 * Metodo setter del valor del peso
	 * @param pesoValor el valor del peso
	 */
	public void setPesoValor(BigDecimal pesoValor) {
		this.pesoValor = pesoValor;
	}

	/**
	 * Metodo getter de la fecha del peso
	 * @return la fecha del peso
	 */
	public Date getFechaPeso() {
		return fechaPeso;
	}

	/**
	 * Metodo setter de la fecha del peso
	 * @param fechaPeso la fecha del peso
	 */
	public void setFechaPeso(Date fechaPeso) {
		this.fechaPeso = fechaPeso;
	}

	/**
	 * Metodo getter del icono del estado del peso
	 * @return el icono del estado del peso
	 */
	public String getIconoEstado() {
		return iconoEstado;
	}

	/**
	 * Metodo setter del icono del estado del peso
	 * @param iconoEstado el icono del estado del peso
	 */
	public void setIconoEstado(String iconoEstado) {
		this.iconoEstado = iconoEstado;
	}


	/**
	 * Enum del estado del peso
	 * @author antonio
	 */
	public enum EstadoPeso {
		AUMENTA("Aumenta el peso"), DISMINUYE("Disminuye el peso"), MANTIENE("Mantiene el peso");

		private String estadoPeso;

		private EstadoPeso(String estadoPeso) {
			this.estadoPeso = estadoPeso;
		}

		public String getEstadoPeso() {
			return this.estadoPeso;
		}

	}

	/**
	 * Metodo getter del estado del peso
	 * @return el estado del peso
	 */
	public EstadoPeso getEstadoPeso() {
		return estadoPeso;
	}

	/**
	 * Metodo setter del estado del peso
	 * @param estadoPeso el estado del peso
	 */
	public void setEstadoPeso(EstadoPeso estadoPeso) {
		this.estadoPeso = estadoPeso;
	}

	
}
