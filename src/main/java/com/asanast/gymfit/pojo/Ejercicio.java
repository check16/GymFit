package com.asanast.gymfit.pojo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ejercicio")
public class Ejercicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEjercicio;
	
	@NotNull
	@Min(0)
	private BigDecimal cargaMax = new BigDecimal("0");
	
	@NotNull
	@Min(0)
	private Integer totalRepeticiones = 0;
	
	@ManyToOne
	@JoinColumn(name="idEntrenamiento")
	private Entrenamiento entrenamiento;
	
	@ManyToOne
	@JoinColumn(name="idTipoEjercicio")
	@NotNull
	private TipoEjercicio tipoEjercicio;
	
	public Ejercicio() {
	}

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

	@Override
	public String toString() {
		return "Ejercicio [idEjercicio=" + idEjercicio + ", cargaMax=" + cargaMax + ", totalRepeticiones="
				+ totalRepeticiones + ", entrenamiento=" + entrenamiento + ", tipoEjercicio=" + tipoEjercicio + "]";
	}
	
	
}