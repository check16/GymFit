package com.asanast.gymfit.dao;

import java.util.Date;
import java.util.List;

import com.asanast.gymfit.pojo.Entrenamiento;
import com.asanast.gymfit.pojo.Usuario;

/**
 * Interface que define el comportamiento para el DAO de entrenamiento
 * @author antonio
 */
public interface EntrenamientoDao {
	
	/**
	 * Metodo para guardar un entrenamiento en la BD
	 * @param entrenamiento el entrenamiento a guardar
	 */
	public void save(Entrenamiento entrenamiento);
	
	/**
	 * Metodo para encontrar un entrenamiento por su id
	 * @param id el id del entrenamiento
	 * @return el entrenamiento
	 */
	public Entrenamiento findById(int id);
	
	/**
	 * Metodo para encontrar todos los entrenamientos
	 * @return la lista de todos los entrenamientos
	 */
	public List<Entrenamiento> findAll();
	
	/**
	 * Metodo para encontrar los entrenamientos de un determinado id de usuario
	 * @param id el id de usuario
	 * @return la lista de los entrenamientos del usuario
	 */
	public List<Entrenamiento> findAllByIdUsuario(int id);
	
	/**
	 * Metodo para encontrar los entrenamientos a partir de un rango de fechas y que tengan un determinado ejercicio
	 * @param usuario el usuario
	 * @param fechaInicio la fecha de inicio de los entrenamientos a buscar
	 * @param fechaFin la fecha de fin de los entrenamientos a buscar
	 * @param idEjercicio el id del ejercicio del entrenamiento
	 * @return la lista de los entrenamientos
	 */
	public List<Entrenamiento> findAllBetweenDateAndEjercicio(Usuario usuario, Date fechaInicio, Date fechaFin, int idEjercicio);
	
	/**
	 * Metodo para actualizar un entrenamiento
	 * @param entrenamiento el entrenamiento a actualizar
	 */
	public void update(Entrenamiento entrenamiento);
	
	/**
	 * Metodo para borrar un entrenamiento
	 * @param entrenamiento el entrenamiento a borrar
	 */
	public void delete(Entrenamiento entrenamiento);	

}
