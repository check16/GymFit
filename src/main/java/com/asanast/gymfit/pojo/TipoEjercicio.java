package com.asanast.gymfit.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tipo_ejercicio")
public class TipoEjercicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoEjercicio;
	
	@NotEmpty
	@NotNull
	@Column(name="nombreEjercicio")
	private String nombreEjercicio;
	
	@NotEmpty
	@NotNull
	@Column(name="descEjercicio")
	private String descEjercicio;
	
	@Column(name="rutaImgEjercicio")
	private String rutaImgEjercicio;
	
	@OneToMany(mappedBy="tipoEjercicio")
	private List<Ejercicio> ejercicios;
	
	public TipoEjercicio() {
		
	}
	
	public TipoEjercicio(String nombreEjercicio, String descEjercicio) {
		this.nombreEjercicio = nombreEjercicio;
		this.descEjercicio = descEjercicio;
	}

	public TipoEjercicio(String nombreEjercicio, String descEjercicio, String rutaImgEjercicio) {
		this.nombreEjercicio = nombreEjercicio;
		this.descEjercicio = descEjercicio;
		this.rutaImgEjercicio = rutaImgEjercicio;
	}

	public int getIdTipoEjercicio() {
		return idTipoEjercicio;
	}

	public void setIdTipoEjercicio(int idTipoEjercicio) {
		this.idTipoEjercicio = idTipoEjercicio;
	}

	public String getNombreEjercicio() {
		return nombreEjercicio;
	}

	public void setNombreEjercicio(String nombreEjercicio) {
		this.nombreEjercicio = nombreEjercicio;
	}

	public String getDescEjercicio() {
		return descEjercicio;
	}

	public void setDescEjercicio(String descEjercicio) {
		this.descEjercicio = descEjercicio;
	}

	public String getRutaImgEjercicio() {
		return rutaImgEjercicio;
	}

	public void setRutaImgEjercicio(String rutaImgEjercicio) {
		this.rutaImgEjercicio = rutaImgEjercicio;
	}

	@Override
	public String toString() {
		return "TipoEjercicio [idTipoEjercicio=" + idTipoEjercicio + ", nombreEjercicio=" + nombreEjercicio
				+ ", descEjercicio=" + descEjercicio + ", rutaImgEjercicio=" + rutaImgEjercicio + ", ejercicios="
				+ ejercicios + "]";
	}
	
	

}
