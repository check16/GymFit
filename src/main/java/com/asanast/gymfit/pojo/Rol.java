package com.asanast.gymfit.pojo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="rol")
public class Rol {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRol;
	
	@NotEmpty
	private String nombreRol;
	
	private String descRol;
	
	@OneToMany(mappedBy="rol")
	private Set<Usuario> usuarios;

	public Rol(String nombreRol, String descRol) {
		this.nombreRol = nombreRol;
		this.descRol = descRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getDescRol() {
		return descRol;
	}

	public void setDescRol(String descRol) {
		this.descRol = descRol;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

}
