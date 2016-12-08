package com.asanast.gymfit.dao;

import java.util.List;

import com.asanast.gymfit.pojo.Rol;

/**
 * Interface que define el comportamiento para el DAO del Rol de usuario
 * @author antonio
 */
public interface RolDao {
	
	/**
	 * Metodo para guardar un rol en la BD
	 * @param rol el rol a guardar
	 */
	public void save(Rol rol);
	
	/**
	 * Metodo para obtener todos los roles de la BD
	 * @return el conjunto de roles
	 */
	public List<Rol> findAll();
	
	/**
	 * Metodo para encontrar un rol por su id
	 * @param id el id del rol
	 * @return el rol
	 */
	public Rol findById(int id);
	
	/**
	 * Metodo para encontrar un rol por su nombre
	 * @param nombreRol el nombre del rol
	 * @return el rol
	 */
	public Rol findByNombreRol(String nombreRol);
	
	/**
	 * Metodo para actualizar el rol
	 * @param rol el rol a actualizar
	 */
	public void update(Rol rol);
	
	/**
	 * Metodo para borrar un rol
	 * @param rol el rol a borrar
	 */
	public void delete(Rol rol);	

}
