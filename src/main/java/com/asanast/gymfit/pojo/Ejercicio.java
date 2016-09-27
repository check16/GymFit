package com.asanast.gymfit.pojo;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Ejercicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEjercicio;
	
	@NotNull
	@NotEmpty
	private BigDecimal cargaMax;
	
	@NotNull
	@NotEmpty
	private int totalRepeticiones;
	
	@ManyToOne
	@JoinColumn(name="idEntrenamiento")
	private Entrenamiento entrenamiento;
	
	@ManyToOne
	@JoinColumn(name="idTipoEjercicio")
	private TipoEjercicio tipoEjercicio;

	public Ejercicio(BigDecimal cargaMax, int totalRepeticiones, Entrenamiento entrenamiento,
			TipoEjercicio tipoEjercicio) {
		this.cargaMax = cargaMax;
		this.totalRepeticiones = totalRepeticiones;
		this.entrenamiento = entrenamiento;
		this.tipoEjercicio = tipoEjercicio;
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	public BigDecimal getCargaMax() {
		return cargaMax;
	}

	public void setCargaMax(BigDecimal cargaMax) {
		this.cargaMax = cargaMax;
	}

	public int getTotalRepeticiones() {
		return totalRepeticiones;
	}

	public void setTotalRepeticiones(int totalRepeticiones) {
		this.totalRepeticiones = totalRepeticiones;
	}

	public Entrenamiento getEntrenamiento() {
		return entrenamiento;
	}

	public void setEntrenamiento(Entrenamiento entrenamiento) {
		this.entrenamiento = entrenamiento;
	}

	public TipoEjercicio getTipoEjercicio() {
		return tipoEjercicio;
	}

	public void setTipoEjercicio(TipoEjercicio tipoEjercicio) {
		this.tipoEjercicio = tipoEjercicio;
	}	
}