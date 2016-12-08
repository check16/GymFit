package com.asanast.gymfit.dao;

import java.util.Date;
import java.util.List;

import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;

/**
 * Interface que define el comportamiento para el DAO del peso
 * @author antonio
 */
public interface PesoDao {
	
	/**
	 * Metodo para guardar un peso en la BD
	 * @param peso el peso a guardar
	 */
	public void save(Peso peso);
	
	/**
	 * Metodo para encontrar los pesos de un id de usuario
	 * @param id el id de usuario
	 * @return el conjunto de pesos del usuario
	 */
	public List<Peso> findAllByIdUsuario(int id);
	
	/**
	 * Metodo para encontrar el peso de un usuario en una fecha
	 * @param usuario el usuario a encontrar su peso
	 * @param fecha la fecha del peso
	 * @return el peso encontrado
	 */
	public Peso findByUsuarioAndFecha(Usuario usuario, Date fecha);
	
	/**
	 * Metodo para encontrar el ultimo peso registrado de un determinado usuario
	 * @param usuario el usuario a encontrar el ultimo peso
	 * @return el peso encontrado
	 */
	public Peso findByLastDate(Usuario usuario);
	
	/**
	 * Metodo para encontrar los pesos X dias atrás del dia actual
	 * @param usuario el usuario a encontrar los pesos
	 * @param dias numero de dias atrás a buscar los pesos
	 * @return el conjunto de pesos encontrado para el usuario
	 */
	public List<Peso> findAllPesoByDias(Usuario usuario, int dias);
	
	/**
	 * Metodo para encontrar los pesos en un rango de fechas para un determinado usuario
	 * @param usuario el usuario a encontrar los pesos
	 * @param fechaInicio fecha de inicio
	 * @param fechaFinal fecha de fin
	 * @return el conjunto de pesos encontrado en ese rango de fechas y usuario
	 */
	public List<Peso> findBetweenDate(Usuario usuario, Date fechaInicio, Date fechaFinal);
	
	/**
	 * Metodo para obtener el promedio del peso de un usuario X dias atrás del día actual
	 * @param usuario el usuario a encontrar los pesos
	 * @param dias numero de dias atrás a buscar los pesos
	 * @return el valor del promedio del peso
	 */
	public Double findAvgPesoByDias(Usuario usuario, int dias);
	
	/**
	 * Metodo para actualizar el peso
	 * @param peso el peso a actualizar
	 */
	public void update(Peso peso);
	
	/**
	 * Metodo para borrar el peso
	 * @param peso el peso a borrar
	 */
	public void delete(Peso peso);
	
	/**
	 * Metodo para actualizar o guardar un peso
	 * @param peso el peso a actualizar o guardar
	 */
	public void saveOrUpdate(Peso peso);
}
