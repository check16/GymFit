package com.asanast.gymfit.pojo.VO;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Clase Value Object para el formulario del IDR
 * @author antonio
 */
public class IdrForm {

	/**
	 * Propiedad que encapsula la edad el usuario
	 */
	@Min(14)
	@Max(120)
	@NotNull
	private int edad;

	/**
	 * Propiedad que encapsula el peso del usuario
	 */
	@NotNull
	@Max(350)
	@Min(30)
	private BigDecimal peso;

	/**
	 * Propiedad que encapsula la altura del usuario
	 */
	@NotNull
	@Min(30)
	@Max(260)
	private int altura;

	/**
	 * Propiedad que encapsula el objetivo del usuario
	 */
	@NotNull
	private Objetivo objetivo;

	/**
	 * Propiedad que encapsula el nivel de actividad del usuario
	 */
	@NotNull
	private Actividad actividad;

	/**
	 * Propiedad que encapsula el sexo del usuario
	 */
	@NotNull
	private Sexo sexo;

	/**
	 * Enum del objetivo del usuario
	 * @author antonio
	 */
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

	/**
	 * Enum del sexo de usuario
	 * @author antonio
	 */
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

	/**
	 * Enum de la actividad del usuario
	 * @author antonio
	 *
	 */
	public enum Actividad {
		SEDENTARIO("Sedentario"), LIGERA("Actividad ligera"), MODERADA("Actividad moderada"), INTENSA(
				"Actividad intensa"), MUYINTENSA("Actividad muy intensa");

		private String actividad;

		private Actividad(String actividad) {
			this.actividad = actividad;
		}

		public String getActividad() {
			return this.actividad;
		}

	}

	/**
	 * Metodo getter de la edad
	 * @return la edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Metodo setter de la edad
	 * @param edad la edad del usuario
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Metodo getter del peso
	 * @return el peso
	 */
	public BigDecimal getPeso() {
		return peso;
	}

	/**
	 * Metodo setter del peso
	 * @param peso el peso del usuario
	 */
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	/**
	 * Metodo getter de la altura
	 * @return la altura
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Metodo setter de la altura
	 * @param altura la altura del usuario
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Metodo getter del objetivo
	 * @return el objetivo del peso del usuario
	 */
	public Objetivo getObjetivo() {
		return objetivo;
	}

	/**
	 * Metodo setter del objetivo
	 * @param objetivo el objetivo del usuario
	 */
	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	/**
	 * Metodo getter del sexo
	 * @return el sexo del usuario
	 */
	public Sexo getSexo() {
		return sexo;
	}

	/**
	 * Metodo setter del sexo del usuario
	 * @param sexo el sexo del usuario
	 */
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	/**
	 * Metodo getter de la actividad del usuario
	 * @return la actividad del usuario
	 */
	public Actividad getActividad() {
		return actividad;
	}

	/**
	 * Metodo setter de la actividad del usuario
	 * @param actividad la actividad del usuario
	 */
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
}
