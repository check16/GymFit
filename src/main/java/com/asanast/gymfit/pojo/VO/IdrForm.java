package com.asanast.gymfit.pojo.VO;

import com.asanast.gymfit.pojo.Peso;

public class IdrForm {

	private int edad;

	private Peso peso;

	private int altura;

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

	public Peso getPeso() {
		return peso;
	}

	public void setPeso(Peso peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	

}
