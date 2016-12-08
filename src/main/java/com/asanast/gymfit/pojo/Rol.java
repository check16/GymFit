package com.asanast.gymfit.pojo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Clase entidad para el mapeo de la BD de la tabla rol
 * @author antonio
 */
@Entity
@Table(name="rol")
public class Rol {
	
	/**
	 * Propiedad que encapsula el id del rol
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRol;
	
	/**
	 * Propiedad que encapsula el nombre del rol
	 */
	@NotEmpty
	private String nombreRol;
	
	/**
	 * Propiedad que encapsula la descripcion del rol
	 */
	private String descRol;
	
	/**
	 * Propiedad que encapsula el conjunto de usuarios
	 */
	@OneToMany(mappedBy="rol", fetch=FetchType.LAZY)
	private Set<Usuario> usuarios;
	
	/**
	 * Constructor por defecto del rol
	 */
	public Rol() {
		
	}

	/**
	 * Constructor con parametros del rol
	 * @param nombreRol el nombre del rol
	 * @param descRol la descripcion del rol
	 */
	public Rol(String nombreRol, String descRol) {
		this.nombreRol = nombreRol;
		this.descRol = descRol;
	}
	
	/**
	 * Metodo getter del nombre del rol
	 * @return el nombre del rol
	 */
	public String getNombreRol() {
		return nombreRol;
	}

	/**
	 * Metodo setter del nombre del rol
	 * @param nombreRol el nombre del rol
	 */
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	/**
	 * Metodo getter de la descripcion del rol
	 * @return la descripcion del rol
	 */
	public String getDescRol() {
		return descRol;
	}

	/**
	 * Metodo setter de la descripcion del rol
	 * @param descRol la descripcion del rol
	 */
	public void setDescRol(String descRol) {
		this.descRol = descRol;
	}

	/**
	 * Metodo getter de los usuarios 
	 * @return los usuarios
	 */
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Metodo setter de los usuarios
	 * @param usuarios los usuarios
	 */
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * Metodo getter del id del rol
	 * @return el id del rol
	 */
	public int getIdRol() {
		return idRol;
	}

	/**
	 * Metodo setter del id del rol 
	 * @param idRol el id del rol
	 */
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	/**
	 * Metodo toString del rol
	 */
	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", nombreRol=" + nombreRol + ", descRol=" + descRol + ", usuarios=" + usuarios
				+ "]";
	}
}
