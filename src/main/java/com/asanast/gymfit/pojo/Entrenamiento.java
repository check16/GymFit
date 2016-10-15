package com.asanast.gymfit.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="entrenamiento")
public class Entrenamiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEntrenamiento;
	
	@NotEmpty
	@NotNull
	private String nombreEntreno;
	
	@NotEmpty
	@NotNull
	private Date fecha;
	
	private String notas;
	
	@OneToMany(mappedBy="entrenamiento")
	private List<Ejercicio> ejercicios = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;

	public Entrenamiento() {
		
	}
	
	public Entrenamiento(String nombreEntreno, Date fecha, String notas, Usuario usuario) {
		this.nombreEntreno = nombreEntreno;
		this.fecha = fecha;
		this.notas = notas;
		this.usuario = usuario;
	}

	public int getIdEntrenamiento() {
		return idEntrenamiento;
	}

	public void setIdEntrenamiento(int idEntrenamiento) {
		this.idEntrenamiento = idEntrenamiento;
	}

	public String getNombreEntreno() {
		return nombreEntreno;
	}

	public void setNombreEntreno(String nombreEntreno) {
		this.nombreEntreno = nombreEntreno;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Entrenamiento [idEntrenamiento=" + idEntrenamiento + ", nombreEntreno=" + nombreEntreno + ", fecha="
				+ fecha + ", notas=" + notas + ", ejercicios=" + ejercicios + ", usuario=" + usuario + "]";
	}
	
	

}
