package com.asanast.gymfit.dao;

import java.util.List;

import com.asanast.gymfit.pojo.Usuario;

/**
 * Interface que define el comportamiento para el DAO del Usuario
 * @author antonio
 */
public interface UsuarioDao {
	
	/**
	 * Metodo para guardar un usuario en la BD
	 * @param usuario el usuaruio a almacenar en la BD
	 */
	public void save(Usuario usuario);
	
	/**
	 * Metodo para buscar todos los usuarios en la BD
	 * @return el conjunto de usuarios
	 */
	public List<Usuario> findAll();
	
	/**
	 * Metodo para encontrar un usuario por su id
	 * @param id el id del usuario a encontrar
	 * @return el usuario encontrado
	 */
	public Usuario findById(int id);
	
	/**
	 * Metodo para encontrar un usuario por su nombre de login
	 * @param login el login a buscar
	 * @return el usuario encontrado
	 */
	public Usuario findByLogin(String login);
	
	/**
	 * Metodo para actualizar un usuario
	 * @param usuario el usuario a actualizar
	 */
	public void update(Usuario usuario);
	
	/**
	 * Metodo para borrar un usuario
	 * @param usuario el usuario a borrar
	 */
	public void delete(Usuario usuario);	
}
