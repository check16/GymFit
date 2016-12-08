package com.asanast.gymfit.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Clase entidad para el mapeo de la BD de la tabla entrenamiento
 * @author antonio
 */
@Entity
@Table(name="entrenamiento")
public class Entrenamiento {
	
	/**
	 * Propiedad que encapsula el id del entrenamiento
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEntrenamiento;
	
	/**
	 * Propiedad que encapsula el nombre de entrenamiento
	 */
	@NotEmpty
	@NotNull
	private String nombreEntreno;
	
	/**
	 * Propiedad que encapsula la fecha del entrenamiento
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date fecha;
	
	/**
	 * Propiedad que encapsula las notas del entrenamiento
	 */
	private String notas;
	
	/**
	 * Propiedad que encapsula los ejerccios del entrenamiento
	 */
	@OneToMany(mappedBy="entrenamiento", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Ejercicio> ejercicios = new ArrayList<Ejercicio>();
	
	/**
	 * Propiedad que encapsula el usuario del entrenamiento
	 */
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;

	/**
	 * Constructor por defecto del entrenamiento
	 */
	public Entrenamiento() {
		
	}
	
	/**
	 * Constructor con parametros del entrenamiento
	 * @param nombreEntreno nombre del entrenamiento
	 * @param fecha fecha del entrenamiento
	 * @param notas notas del entrenamiento
	 * @param usuario usuario del entrenamiento
	 */
	public Entrenamiento(String nombreEntreno, Date fecha, String notas, Usuario usuario) {
		this.nombreEntreno = nombreEntreno;
		this.fecha = fecha;
		this.notas = notas;
		this.usuario = usuario;
	}

	/**
	 * Metodo getter del id del entrenamiento
	 * @return el id del entrenamiento
	 */
	public int getIdEntrenamiento() {
		return idEntrenamiento;
	}

	/**
	 * Metodo setter del id del entrenamiento
	 * @param idEntrenamiento el id del entrenamiento
	 */
	public void setIdEntrenamiento(int idEntrenamiento) {
		this.idEntrenamiento = idEntrenamiento;
	}

	/**
	 * Metodo getter del nombre del entrenamiento
	 * @return el nombre del entrenamiento
	 */
	public String getNombreEntreno() {
		return nombreEntreno;
	}

	/**
	 * Metodo setter del nombre del entrenamiento
	 * @param nombreEntreno el nombre del entrenamiento
	 */
	public void setNombreEntreno(String nombreEntreno) {
		this.nombreEntreno = nombreEntreno;
	}

	/**
	 * Metodo getter de la fecha del entrenamiento
	 * @return la fecha del entrenamiento
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Metodo setter de la fecha del entrenamiento
	 * @param fecha la fecha del entrenamiento
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Metodo getter de las notas del entrenamiento
	 * @return las notas del entrenamiento
	 */
	public String getNotas() {
		return notas;
	}

	/**
	 * Metodo setter de las notas del entrenamiento
	 * @param notas las notas del entrenamiento
	 */
	public void setNotas(String notas) {
		this.notas = notas;
	}

	/**
	 * Metodo getter del usuario del entrenamiento
	 * @return el usuario del entrenamiento
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Metodo setter del usuario del entrenamiento
	 * @param usuario el usuario del entrenamiento
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Metodo getter de los ejercicios del entrenamiento
	 * @return los ejercicios del entrenamiento
	 */
	public List<Ejercicio> getEjercicios() {
		return ejercicios;
	}

	/**
	 * Metodo setter de los ejercicios del entrenamiento
	 * @param ejercicios los ejercicios del entrenamiento
	 */
	public void setEjercicios(List<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}
	
	/**
	 * Metodo toString del entrenamiento
	 */
	@Override
	public String toString() {
		return "Entrenamiento [idEntrenamiento=" + idEntrenamiento + ", nombreEntreno=" + nombreEntreno + ", fecha="
				+ fecha + ", notas=" + notas + ", ejercicios=" + ejercicios + ", usuario=" + usuario + "]";
	}

}
