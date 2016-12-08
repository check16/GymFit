package com.asanast.gymfit.pojo.VO;
/**
 * Clase Value Object para el formulario de cambio de contraseña
 * @author antonio
 */
public class PasswordForm {

	/**
	 * Propiedad que encapsula la clave del usuario
	 */
	private String clave;
	
	/**
	 * Propiedad que encapsula la repeticion de la clave
	 */
	private String repiteClave;
	
	/**
	 * Constructor por defecto de la clase
	 */
	public PasswordForm() {
		
	}

	/**
	 * Metodo getter de la clave
	 * @return la clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Metodo setter de la clave
	 * @param clave la clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Metodo getter de la clave repetida
	 * @return la clave repetida
	 */
	public String getRepiteClave() {
		return repiteClave;
	}

	/**
	 * Metodo setter de la clave repetida
	 * @param clave la clave repetida
	 */
	public void setRepiteClave(String repiteClave) {
		this.repiteClave = repiteClave;
	}
	
	
}
