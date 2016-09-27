package com.asanast.gymfit.pojo;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Peso {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRegistroPeso;
	
	@NotEmpty
	@NotNull
	private BigDecimal peso;
	
	@NotEmpty
	@NotNull
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	

	public Peso(BigDecimal peso, Date fecha, Usuario usuario) {
		this.peso = peso;
		this.fecha = fecha;
		this.usuario = usuario;
	}

	public int getIdRegistroPeso() {
		return idRegistroPeso;
	}

	public void setIdRegistroPeso(int idRegistroPeso) {
		this.idRegistroPeso = idRegistroPeso;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
