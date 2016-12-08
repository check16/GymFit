package com.asanast.gymfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.RolDao;
import com.asanast.gymfit.pojo.Rol;

/**
 * Clase de servicio para el Rol
 * @author antonio
 */
@Service
public class RolService {

	/**
	 * Propiedad que encapsula el DAO del rol
	 */
	@Autowired
	private RolDao rolDao;

	/**
	 * Metodo para guardar un rol
	 * @param rol el rol a guardar
	 */
	public void save(Rol rol) {
		rolDao.save(rol);
	}

	/**
	 * Metodo para buscar todos los roles
	 * @return el conjunto de roles
	 */
	public List<Rol> findAll() {
		return rolDao.findAll();
	}

	/**
	 * Metodo para buscar un rol a partir de su nombre
	 * @param nombreRol el nombre del rol
	 * @return el rol
	 */
	public Rol findByNombreRol(String nombreRol) {
		return rolDao.findByNombreRol(nombreRol);
	}
	
	/**
	 * Metodo para buscar un rol por su id
	 * @param id el id del rol
	 * @return el rol
	 */
	public Rol findByIdRol(int id) {
		return rolDao.findById(id);
	}

	/**
	 * Metodo para actualizar un rol
	 * @param rol el rol
	 */
	public void update(Rol rol) {
		rolDao.update(rol);
	}

	/**
	 * Metodo para borrar un rol
	 * @param rol el rol a borrar
	 */
	public void delete(Rol rol) {
		rolDao.delete(rol);
	}
}
