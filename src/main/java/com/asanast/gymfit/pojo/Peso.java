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

@Entity
@Table(name="peso")
public class Peso implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@Max(350)
	@Min(30)
	private BigDecimal pesoReg;
	
	@NotNull
	@Past
	@Id
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	@Id
	private Usuario usuario;
	
	public Peso() {
		
	}

	public Peso(BigDecimal peso, Date fecha, Usuario usuario) {
		this.pesoReg = peso;
		this.fecha = fecha;
		this.usuario = usuario;
	}

	public BigDecimal getPesoReg() {
		return pesoReg;
	}

	public void setPesoReg(BigDecimal pesoReg) {
		this.pesoReg = pesoReg;
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
