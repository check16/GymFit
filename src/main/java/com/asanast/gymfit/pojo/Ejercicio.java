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

/**
 * Clase entidad para el mapeo de la BD de la tabla ejercicio
 * @author antonio
 */
@Entity
@Table(name="ejercicio")
public class Ejercicio {
	
	/**
	 * Propiedad que encapsula el id del ejercicio
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEjercicio;
	
	/**
	 * Propiedad que encapsula la carga maxima del ejercicio
	 */
	@NotNull
	@Min(0)
	private BigDecimal cargaMax = new BigDecimal("0");
	
	/**
	 * Propiedad que encapsula el total de repeticiones del ejercicio
	 */
	@NotNull
	@Min(0)
	private Integer totalRepeticiones = 0;
	
	/**
	 * Propiedad que encapsula el entrenamiento al que pertenece el ejercicio
	 */
	@ManyToOne
	@JoinColumn(name="idEntrenamiento")
	private Entrenamiento entrenamiento;
	
	/**
	 * Propiedad que encapsula el tipo de ejercicio 
	 */
	@ManyToOne
	@JoinColumn(name="idTipoEjercicio")
	@NotNull
	private TipoEjercicio tipoEjercicio;
	
	/**
	 * Constructor por defecto del Ejercicio
	 */
	public Ejercicio() {
	}

	/**
	 * Constructor con parametros del Ejercicio
	 * @param cargaMax la carga maxima del ejercicio
	 * @param totalRepeticiones el total de repeticiones del ejercicio
	 * @param entrenamiento el entrenamiento del ejercicio
	 * @param tipoEjercicio el tipo de ejercicio
	 */
	public Ejercicio(BigDecimal cargaMax, int totalRepeticiones, Entrenamiento entrenamiento,
			TipoEjercicio tipoEjercicio) {
		this.cargaMax = cargaMax;
		this.totalRepeticiones = totalRepeticiones;
		this.entrenamiento = entrenamiento;
		this.tipoEjercicio = tipoEjercicio;
	}

	/**
	 * Metodo getter del id del ejercicio
	 * @return el id del ejercicio
	 */
	public int getIdEjercicio() {
		return idEjercicio;
	}

	/**
	 * Metodo setter del id del ejercicio
	 * @param idEjercicio el id del ejercicio
	 */
	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	/**
	 * Metodo getter de la carga maxima del ejercicio
	 * @return
	 */
	public BigDecimal getCargaMax() {
		return cargaMax;
	}

	/**
	 * Metodo setter de la carga maxima del ejercicio
	 * @param cargaMax la carga maxima del ejercicio
	 */
	public void setCargaMax(BigDecimal cargaMax) {
		this.cargaMax = cargaMax;
	}

	/**
	 * Metodo getter del total de repeticiones del ejercicio
	 * @return
	 */
	public int getTotalRepeticiones() {
		return totalRepeticiones;
	}

	/**
	 * Metodo setter del total de repeticiones del ejercicio
	 * @param totalRepeticiones el total de repeticiones del ejercicio
	 */
	public void setTotalRepeticiones(int totalRepeticiones) {
		this.totalRepeticiones = totalRepeticiones;
	}

	/**
	 * Metodo getter del entrenamiento
	 * @return el entrenamiento del ejercicio
	 */
	public Entrenamiento getEntrenamiento() {
		return entrenamiento;
	}

	/**
	 * Metodo setter del entrenamiento del ejercicio
	 * @param entrenamiento el entrenamiento del ejercicio
	 */
	public void setEntrenamiento(Entrenamiento entrenamiento) {
		this.entrenamiento = entrenamiento;
	}

	/**
	 * Metodo getter del tipo de ejercicio
	 * @return el tipo de ejercicio
	 */
	public TipoEjercicio getTipoEjercicio() {
		return tipoEjercicio;
	}

	/**
	 * Metodo setter del tipo de ejercicio
	 * @param tipoEjercicio el tipo de ejercicio
	 */
	public void setTipoEjercicio(TipoEjercicio tipoEjercicio) {
		this.tipoEjercicio = tipoEjercicio;
	}

	/**
	 * Metodo toString del Ejercicio
	 */
	@Override
	public String toString() {
		return "Ejercicio [idEjercicio=" + idEjercicio + ", cargaMax=" + cargaMax + ", totalRepeticiones="
				+ totalRepeticiones + ", entrenamiento=" + entrenamiento + ", tipoEjercicio=" + tipoEjercicio + "]";
	}
	
	
}