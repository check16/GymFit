package com.asanast.gymfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.EjercicioDao;
import com.asanast.gymfit.pojo.Ejercicio;
import com.asanast.gymfit.pojo.TipoEjercicio;

/**
 * Clase de servicio para el Ejercicio
 * @author antonio
 */
@Service
public class EjercicioService {
	
	/**
	 * Propiedad que encapsula el DAO del ejercicio
	 */
	@Autowired
	private EjercicioDao ejercicioDao;
	
	/**
	 * Metodo para guardar un ejercicio
	 * @param ejercicio el ejercicio a guardar
	 * @param tipoEjercicio el tipo de ejercicio
	 */
	public void save(Ejercicio ejercicio, TipoEjercicio tipoEjercicio) {
		ejercicio.setTipoEjercicio(tipoEjercicio);
		ejercicioDao.save(ejercicio);
	}
	
	/**
	 * Metodo para buscar todos los entrenamientos a partir del id del mismo
	 * @param id el id del entrenamiento
	 * @return el conjunto de ejercicios
	 */
	public List<Ejercicio> findAllByIdEntrenamiento(int id) {
		return ejercicioDao.findAllByIdEntrenamiento(id);
		
	}
	
	/**
	 * Metodo para encontrar un ejercicio a partir de su id
	 * @param id el id del ejercicio
	 * @return el ejercicio
	 */
	public Ejercicio findByIdEjercicio(int id) {
		return ejercicioDao.findByIdEjercicio(id);
		
	}
	
	/**
	 * Metodo para actualizar el ejercicio
	 * @param ejercicio el ejercicio a actualizar
	 */
	public void update(Ejercicio ejercicio) {
		ejercicioDao.update(ejercicio);
	}
	
	/**
	 * Metodo para borrar el ejercicio
	 * @param ejercicio el ejercicio a borrar
	 */
	public void delete(Ejercicio ejercicio) {
		ejercicioDao.delete(ejercicio);
	}

}