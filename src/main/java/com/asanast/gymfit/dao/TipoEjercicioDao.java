package com.asanast.gymfit.dao;

import java.util.List;

import com.asanast.gymfit.pojo.TipoEjercicio;

/**
 * Interface que define el comportamiento para el DAO del Tipo de ejercicio
 * @author antonio
 */
public interface TipoEjercicioDao {
	
	/**
	 * Metodo para almacenar un tipo de ejercicio en la BD
	 * @param tipoEjercicio el tipo de ejercicio a almancenar
	 */
	public void save(TipoEjercicio tipoEjercicio);
	
	/**
	 * Metodo para encontrar todos los tipos de ejercicios de la BD
	 * @return el conjunto de tipos de ejercicios
	 */
	public List<TipoEjercicio> findAll();
	
	/**
	 * Metodo para encontrar un tipo de ejercicio por su id
	 * @param id el id del tipo de ejercicio
	 * @return el tipo de ejercicio encontrado
	 */
	public TipoEjercicio findById(int id);
	
	/**
	 * Metodo para encontrar un tipo de ejercicio por su nombre
	 * @param nombreEjercicio el nombre del ejercicio a buscar
	 * @return el tipo de ejercicio encontrado
	 */
	public TipoEjercicio findByNombreEjercicio(String nombreEjercicio);
	
	/**
	 * Metodo para actualizar el tipo de ejercicio
	 * @param tipoEjercicio el tipo de ejercicio a actualizar
	 */
	public void update(TipoEjercicio tipoEjercicio);
	
	/**
	 * Metodo para borrar un tipo de ejercicio 
	 * @param tipoEjercicio el tipo de ejercicio a borrar
	 */
	public void delete(TipoEjercicio tipoEjercicio);

}
