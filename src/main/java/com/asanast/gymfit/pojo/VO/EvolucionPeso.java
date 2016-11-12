package com.asanast.gymfit.pojo.VO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EvolucionPeso {

	private List<String> fechas;
	private List<BigDecimal> pesos;

	public EvolucionPeso() {
		fechas = new ArrayList<String>();
		pesos = new ArrayList<BigDecimal>();
	}

	public List<String> getFechas() {
		return fechas;
	}

	public void setFechas(List<String> fechas) {
		this.fechas = fechas;
	}

	public List<BigDecimal> getPesos() {
		return pesos;
	}

	public void setPesos(List<BigDecimal> pesos) {
		this.pesos = pesos;
	}
	
	

}
