package com.asanast.gymfit.dao;

import java.util.List;

import com.asanast.gymfit.pojo.Ejercicio;

/**
 * Interface que define el comportamiento para el DAO de ejercicio
 * @author antonio
 */
public interface EjercicioDao{
	
	/**
	 * Metodo para guardar un Ejercicio en la BD.
	 * @param ejercicio el Ejercicio a guardar.
	 */
	public void save(Ejercicio ejercicio);
	
	/**
	 * Metodo para encontrar un ejercicio por su id.
	 * @param id el id del ejercicio
	 * @return el ejercicio cuyo id es el id proporcionado
	 */
	public Ejercicio findByIdEjercicio(int id);
	
	/**
	 * Metodo para encontrar todos los ejercicios de un determinado id de entrenamiento
	 * @param id el id del entrenamiento
	 * @return la lista de ejercicicios del id de entrenamiento proporcionado
	 */
	public List<Ejercicio> findAllByIdEntrenamiento(int id);
	
	/**
	 * Metodo para actualizar un ejercicio
	 * @param ejercicio el ejercicio a actualizar
	 */
	public void update(Ejercicio ejercicio);
	
	/**
	 * Metodo para borrar un ejercicio
	 * @param ejercicio el ejercicio a borrar
	 */
	public void delete(Ejercicio ejercicio);

}
