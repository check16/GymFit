package com.asanast.gymfit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.EntrenamientoDao;
import com.asanast.gymfit.pojo.Entrenamiento;
import com.asanast.gymfit.pojo.Usuario;

/**
 * Clase de servicio para el Entrenamiento
 * @author antonio
 */
@Service
public class EntrenamientoService {

	/**
	 * Propiedad que encapsula el DAO del entrenamiento
	 */
	@Autowired
	private EntrenamientoDao entrenamientoDao;

	/**
	 * Metodo que guarda un entrenamiento en la BD
	 * 
	 * @param entrenamiento
	 *            el entrenamiento a guardar
	 * @param usuario
	 *            el usuario del entrenamiento
	 */
	public void save(Entrenamiento entrenamiento, Usuario usuario) {
		entrenamiento.setUsuario(usuario);
		entrenamientoDao.save(entrenamiento);
	}

	/**
	 * Metodo para buscar un entrenamiento a partir de su id
	 * 
	 * @param id
	 *            el id del entrenamiento
	 * @return el entrenamiento
	 */
	public Entrenamiento findById(int id) {
		return entrenamientoDao.findById(id);
	}

	/**
	 * Metodo para buscar todos los entrenamientos
	 * 
	 * @return el conjunto de entrenamientos
	 */
	public List<Entrenamiento> findAll() {
		return entrenamientoDao.findAll();
	}

	/**
	 * Metodo para buscar los entrenamientos de un determinado id de usuario
	 * 
	 * @param id
	 *            el id de usuario
	 * @return el conjunto de entrenamientos del usuario
	 */
	public List<Entrenamiento> findAllByIdUsuario(int id) {
		return entrenamientoDao.findAllByIdUsuario(id);

	}

	/**
	 * Metodo para buscar los entrenamientos de un usuario entre un conjunto de
	 * fechas y que contienen un ejercicio determinado para determinar la
	 * evolucion de la fuerza
	 * 
	 * @param usuario el usuario
	 * @param fechaInicio la fecha de inicio del entrenamiento
	 * @param fechaFin la fecha de fin del entrenamiento
	 * @param idEjercicio el id del ejercicio
	 * @return el conjunto de entrenamientos que cumplen esos requisitos
	 */
	public List<Entrenamiento> findFuerzaEjercicioUsuarioBetweenDates(Usuario usuario, Date fechaInicio, Date fechaFin,
			int idEjercicio) {
		return entrenamientoDao.findAllBetweenDateAndEjercicio(usuario, fechaInicio, fechaFin, idEjercicio);
	}

	/**
	 * Metodo para actualizar el entrenamiento
	 * @param entrenamiento el entrenamiento a actualizar
	 */
	public void update(Entrenamiento entrenamiento) {
		entrenamientoDao.update(entrenamiento);
	}

	/**
	 * Metodo para borrar un entrenamiento
	 * @param entrenamiento el entrenamiento a borrar
	 */
	public void delete(Entrenamiento entrenamiento) {
		entrenamientoDao.delete(entrenamiento);
	}

}
