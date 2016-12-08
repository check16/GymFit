package com.asanast.gymfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.TipoEjercicioDao;
import com.asanast.gymfit.pojo.TipoEjercicio;

/**
 * Clase de servicio para el tipo de ejercicio
 * @author antonio
 */
@Service
public class TipoEjercicioService {
	
	/**
	 * Propiedad que encapsula el DAO del tipo de ejercicio
	 */
	@Autowired
	private TipoEjercicioDao tipoEjercicioDao;

	/**
	 * Metodo para guardar un tipo de ejercicio
	 * @param tipoEjercicio el tipo de ejercicio a guardar
	 */
	public void save(TipoEjercicio tipoEjercicio) {
		tipoEjercicioDao.save(tipoEjercicio);
	}

	/**
	 * Metodo para buscar todos los tipos de ejercicios
	 * @return el conjunto de tipos de ejercicio
	 */
	public List<TipoEjercicio> findAll() {
		return tipoEjercicioDao.findAll();
	}

	/**
	 * Metodo para buscar un tipo de ejercicio por su nombre
	 * @param nombreEjercicio el nombre del tipo de ejercicio
	 * @return el tipo de ejercicio
	 */
	public TipoEjercicio findByNombreEjercicio(String nombreEjercicio) {
		return tipoEjercicioDao.findByNombreEjercicio(nombreEjercicio);
	}

	/**
	 * Metodo para actualizar el tipo de ejercicio
	 * @param tipoEjercicio el tipo de ejercicio a actualizar
	 */
	public void update(TipoEjercicio tipoEjercicio) {
		tipoEjercicioDao.update(tipoEjercicio);
	}

	/**
	 * Metodo para borrar el tipo de ejercicio
	 * @param tipoEjercicio el tipo de ejercicio a borrar
	 */
	public void delete(TipoEjercicio tipoEjercicio) {
		tipoEjercicioDao.delete(tipoEjercicio);
	}

}
