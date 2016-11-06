package com.asanast.gymfit.pojo.VO;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class IdrForm {

	@Min(14)
	@Max(120)
	@NotNull
	private int edad;

	@NotNull
	@Max(350)
	@Min(30)
	private BigDecimal peso;

	@NotNull
	@Min(30)
	@Max(260)
	private int altura;
	
	@NotNull
	private Objetivo objetivo;
	
	@NotNull
	private Sexo sexo;

	public enum Objetivo {
		AUMENTAR("Aumentar de peso"), AUMENTAR_LENTAMENTE("Aumentar de peso lentamente"), MANTENER(
				"Mantener el peso"), PERDER_LENTAMENTE("Perder peso lentamente"), PERDER("Perder peso");

		private String objetivo;

		private Objetivo(String objetivo) {
			this.objetivo = objetivo;
		}

		public String getObjetivo() {
			return this.objetivo;
		}

	}

	public enum Sexo {
		M("Masculino"), F("Femenino");

		private String sexo;

		private Sexo(String sexo) {
			this.sexo = sexo;
		}

		public String getSexo() {
			return this.sexo;
		}

	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
}
