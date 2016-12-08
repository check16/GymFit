package com.asanast.gymfit.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Clase entidad para el mapeo de la BD de la tabla tipo_ejercicio
 * @author antonio
 */
@Entity
@Table(name="tipo_ejercicio")
public class TipoEjercicio {
	
	/**
	 * Propiedad que encapsula el id del tipo de ejercicio
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoEjercicio;
	
	/**
	 * Propiedad que encapsula el nombre del ejercicio
	 */
	@NotEmpty
	@NotNull
	private String nombreEjercicio;

	/**
	 * Propiedad que encapsula la descripcion del ejercicio
	 */
	private String descEjercicio;

	/**
	 * Propiedad que encapsula la ruta de imagen del ejercicio
	 */
	private String rutaImgEjercicio;
	
	/**
	 * Propiedad que encapsula la url del video del ejercicio
	 */
	private String urlVideo;
	
	
	/**
	 * Propiedad que encapsula el conjunto de ejercicios
	 */
	@OneToMany(mappedBy="tipoEjercicio", fetch=FetchType.EAGER)
	private List<Ejercicio> ejercicios;
	
	/**
	 * Constructor por defecto de la clase
	 */
	public TipoEjercicio() {
		
	}

	/**
	 * Metodo getter del id del tipo de ejercicio
	 * @return el id del tipo de ejercicio
	 */
	public int getIdTipoEjercicio() {
		return idTipoEjercicio;
	}

	/**
	 * Metodo setter del id del tipo de ejercicio
	 * @param idTipoEjercicio el id del tipo de ejercicio
	 */
	public void setIdTipoEjercicio(int idTipoEjercicio) {
		this.idTipoEjercicio = idTipoEjercicio;
	}

	/**
	 * Metodo getter del nombre del ejercicio
	 * @return el nombre del ejercicio
	 */
	public String getNombreEjercicio() {
		return nombreEjercicio;
	}

	/**
	 * Metodo setter del nombre del ejercicio
	 * @param nombreEjercicio el nombre del ejercicio
	 */
	public void setNombreEjercicio(String nombreEjercicio) {
		this.nombreEjercicio = nombreEjercicio;
	}

	/**
	 * Metodo getter de la descripcion del ejercicio
	 * @return la descripcion del ejercicio
	 */
	public String getDescEjercicio() {
		return descEjercicio;
	}

	/**
	 * Metodo setter de la descripcion del ejercicio
	 * @param descEjercicio la descripcion del ejercicio
	 */
	public void setDescEjercicio(String descEjercicio) {
		this.descEjercicio = descEjercicio;
	}

	/**
	 * Metodo getter de la ruta de imagen del ejercicio
	 * @return la ruta de imagen del ejercicio
	 */
	public String getRutaImgEjercicio() {
		return rutaImgEjercicio;
	}

	/**
	 * Metodo setter de la ruta de imagen del ejercicio
	 * @param rutaImgEjercicio
	 */
	public void setRutaImgEjercicio(String rutaImgEjercicio) {
		this.rutaImgEjercicio = rutaImgEjercicio;
	}

	/**
	 * Metodo getter de la url del video del ejercicio
	 * @return la url del video del ejercicio
	 */
	public String getUrlVideo() {
		return urlVideo;
	}

	/**
	 * Metodo setter de la url del video del ejercicio
	 * @param urlVideo la url del video del ejercicio
	 */
	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}
}
