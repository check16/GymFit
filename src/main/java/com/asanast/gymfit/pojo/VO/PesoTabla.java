package com.asanast.gymfit.pojo.VO;

import java.math.BigDecimal;
import java.util.Date;

public class PesoTabla {

	private BigDecimal pesoValor;
	private Date fechaPeso;
	private EstadoPeso estadoPeso;

	public PesoTabla() {

	}

	public PesoTabla(BigDecimal pesoValor, Date fechaPeso) {
		this.pesoValor = pesoValor;
		this.fechaPeso = fechaPeso;
		
	}

	public BigDecimal getPesoValor() {
		return pesoValor;
	}

	public void setPesoValor(BigDecimal pesoValor) {
		this.pesoValor = pesoValor;
	}

	public Date getFechaPeso() {
		return fechaPeso;
	}

	public void setFechaPeso(Date fechaPeso) {
		this.fechaPeso = fechaPeso;
	}

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

	public EstadoPeso getEstadoPeso() {
		return estadoPeso;
	}

	public void setEstadoPeso(EstadoPeso estadoPeso) {
		this.estadoPeso = estadoPeso;
	}

	
}
