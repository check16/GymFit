package com.asanast.gymfit.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Table(name="peso")
public class Peso {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRegistroPeso;
	
	@NotNull
	@Max(350)
	@Min(0)
	private BigDecimal pesoReg;
	
	@NotNull
	@Past
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	public Peso() {
		
	}

	public Peso(BigDecimal peso, Date fecha, Usuario usuario) {
		this.pesoReg = peso;
		this.fecha = fecha;
		this.usuario = usuario;
	}

	public int getIdRegistroPeso() {
		return idRegistroPeso;
	}

	public void setIdRegistroPeso(int idRegistroPeso) {
		this.idRegistroPeso = idRegistroPeso;
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

	@Override
	public String toString() {
		return "Peso [idRegistroPeso=" + idRegistroPeso + ", peso=" + pesoReg + ", fecha=" + fecha + ", usuario=" + usuario
				+ "]";
	}

}
