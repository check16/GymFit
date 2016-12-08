package com.asanast.gymfit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.PesoDao;
import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;

/**
 * Clase de servicio para el Peso
 * @author antonio
 */
@Service
public class PesoService {
	
	/**
	 * Propiedad que encapsula el DAO del peso
	 */
	@Autowired
	private PesoDao pesoDao;
	
	/**
	 * Metodo que guarda un peso en la base de datos
	 * @param peso el peso a guardar
	 */
	public void save(Peso peso) {
		pesoDao.save(peso);
	}
	
	/**
	 * Metodo que busca los pesos de un usuario
	 * @param usuario el usuario a buscar los pesos
	 * @return el conjunto de pesos
	 */
	public List<Peso> findAllByIdUsuario(Usuario usuario) {
		return pesoDao.findAllByIdUsuario(usuario.getIdUsuario());
	}
	
	/**
	 * Metodo que busca los pesos de un usuario X dias antes del dia actual
	 * @param usuario el usuario a buscar los pesos
	 * @param dias el numero de dias antes del actual a buscar los pesos
	 * @return el conjunto de pesos
	 */
	public List<Peso> findAllByLastDay(Usuario usuario, int dias) {
		return pesoDao.findAllPesoByDias(usuario, dias);
	}
	
	/**
	 * Metodo para buscar el peso de un usuario en una fecha
	 * @param usuario el usuario a buscar el peso
	 * @param fecha la fecha del peso 
	 * @return el peso con esos criterios de busqueda
	 */
	public Peso findByUsuarioAndFecha(Usuario usuario, Date fecha) {
		return pesoDao.findByUsuarioAndFecha(usuario, fecha);
	}
	
	/**
	 * Metodo para encontrar el conjunto de pesos a partir de un intervalo de fechas de un usuario
	 * @param usuario el usuario a buscar los pesos
	 * @param fechaInicio la fecha de inicio
	 * @param fechaFin la fecha de fin
	 * @return el conjunto de pesos
	 */
	public List<Peso> findPesoUsuarioBetweenDates(Usuario usuario, Date fechaInicio, Date fechaFin) {
		return pesoDao.findBetweenDate(usuario, fechaInicio, fechaFin);
	}
	
	/**
	 * Metodo para guardar o actualizar un peso
	 * @param peso el peso a actualizar o guardar
	 */
	public void saveOrUpdate(Peso peso) {
		pesoDao.saveOrUpdate(peso);
	}
	
	/**
	 * Metodo para obtener el ultimo peso registrado de un determinado usuario
	 * @param usuario el usuario a buscar el peso
	 * @return el peso
	 */
	public Peso findLastPeso(Usuario usuario) {
		return pesoDao.findByLastDate(usuario);
	}
	
	/**
	 * Metodo para actualizar el peso del usuario
	 * @param peso el peso del usuario
	 */
	public void update(Peso peso) {
		pesoDao.update(peso);
	}
	
	/**
	 * Metodo para borrar el peso de un usuario
	 * @param peso el peso del usuario
	 */
	public void delete(Peso peso) {
		pesoDao.delete(peso);
	}
	
	/**
	 * Metodo para obtener el promedio de peso de un usuario X dias atras del dia actual
	 * @param usuario el usuario
	 * @param dias numero de dias atras del dia actual para conocer el promedio del peso
	 * @return el promedio del peso
	 */
	public Double avgPesoFromLastDias(Usuario usuario, int dias) {
		return pesoDao.findAvgPesoByDias(usuario, dias);
	}

}
