package com.asanast.gymfit.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Clase entidad para el mapeo de la BD de la tabla peso
 * @author antonio
 */
@Entity
@Table(name="peso")
public class Peso implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Propiedad que encapsula el peso registrado
	 */
	@NotNull
	@Max(350)
	@Min(30)
	private BigDecimal pesoReg;
	
	/**
	 * Propiedad que encapsula la fecha del peso registrado
	 */
	@NotNull
	@Past
	@Id
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fecha;
	
	/**
	 * Propiedad que encapsula el usuario
	 */
	@ManyToOne
	@JoinColumn(name="idUsuario")
	@Id
	private Usuario usuario;

	/**
	 * Constructor por defecto
	 */
	public Peso() {
		
	}

	/**
	 * Constructor con parametros
	 * @param peso el peso
	 * @param fecha la fecha del peso
	 * @param usuario el usuario del peso
	 */
	public Peso(BigDecimal peso, Date fecha, Usuario usuario) {
		this.pesoReg = peso;
		this.fecha = fecha;
		this.usuario = usuario;
	}

	/**
	 * Metodo getter del peso registrado
	 * @return el peso registrado
	 */
	public BigDecimal getPesoReg() {
		return pesoReg;
	}

	/**
	 * Metodo setter del peso registrado
	 * @param pesoReg el peso registrado
	 */
	public void setPesoReg(BigDecimal pesoReg) {
		this.pesoReg = pesoReg;
	}

	/**
	 * Metodo getter de la fecha del peso
	 * @return la fecha del peso
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Metodo setter de la fecha
	 * @param fecha la fecha del peso
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Metodo getter del usuario
	 * @return el usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Metodo setter del usuario
	 * @param usuario el usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
