package com.asanast.gymfit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.pojo.Ejercicio;

/**
 * Clase repositorio de implementacion del DAO de Ejercicio
 * @author antonio
 */
@Transactional
@Repository
public class EjercicioDaoImpl implements EjercicioDao {
	
	/**
	 * Propiedad que encapsula la factoria de sesion de Hibernate
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Metodo getter para obtener la sesion actual a partir de la factoria de Hibernate.
	 * @return la sesion actual
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Metodo para guardar un Ejercicio en la BD.
	 * @param ejercicio el Ejercicio a guardar.
	 */
	@Override
	public void save(Ejercicio ejercicio) {
		getSession().save(ejercicio);
		getSession().getTransaction().commit();
		
	}

	/**
	 * Metodo para encontrar todos los ejercicios de un determinado id de entrenamiento
	 * @param id el id del entrenamiento
	 * @return la lista de ejercicicios del id de entrenamiento proporcionado
	 */
	@Override
	public List<Ejercicio> findAllByIdEntrenamiento(int id) {
		Criteria crit = getSession().createCriteria(Ejercicio.class);
		crit.createAlias("entrenamiento", "entrenamiento");
		crit.add(Restrictions.eq("entrenamiento.idEntrenamiento", id));
		final List<Ejercicio> ejercicios = new ArrayList<>();
		for(final Object o : crit.list()) {
			ejercicios.add((Ejercicio)o);
		}
		return ejercicios;
	}
	
	/**
	 * Metodo para actualizar un ejercicio
	 * @param ejercicio el ejercicio a actualizar
	 */
	@Override
	public void update(Ejercicio ejercicio) {
		getSession().update(ejercicio);
		
	}

	/**
	 * Metodo para borrar un ejercicio
	 * @param ejercicio el ejercicio a borrar
	 */
	@Override
	public void delete(Ejercicio ejercicio) {
		getSession().delete(ejercicio);
		
	}

	/**
	 * Metodo para encontrar un ejercicio por su id.
	 * @param id el id del ejercicio
	 * @return el ejercicio cuyo id es el id proporcionado
	 */
	@Override
	public Ejercicio findByIdEjercicio(int id) {
		Criteria criteria = getSession().createCriteria(Ejercicio.class);
		criteria.add(Restrictions.eq("idEjercicio", id));
		
		return (Ejercicio) criteria.uniqueResult();
	}

}
