package com.asanast.gymfit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.pojo.TipoEjercicio;

/**
 * Clase repositorio de implementacion del DAO del Tipo de ejercicio
 * @author antonio
 */
@Transactional
@Repository
public class TipoEjercicioImpl implements TipoEjercicioDao{
	
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
	 * Metodo para almacenar un tipo de ejercicio en la BD
	 * @param tipoEjercicio el tipo de ejercicio a almancenar
	 */
	@Override
	public void save(TipoEjercicio tipoEjercicio) {
		getSession().save(tipoEjercicio);
		getSession().getTransaction().commit();
		
	}

	/**
	 * Metodo para encontrar todos los tipos de ejercicios de la BD
	 * @return el conjunto de tipos de ejercicios
	 */
	@Override
	public List<TipoEjercicio> findAll() {
		Query query = getSession().createQuery("from TipoEjercicio");
		final List<TipoEjercicio> tipoEjercicios = new ArrayList<>();
		for(final Object o : query.list()) {
			tipoEjercicios.add((TipoEjercicio)o);
		}
		return tipoEjercicios;
	}

	/**
	 * Metodo para encontrar un tipo de ejercicio por su id
	 * @param id el id del tipo de ejercicio
	 * @return el tipo de ejercicio encontrado
	 */
	@Override
	public TipoEjercicio findById(int id) {
		Criteria criteria = getSession().createCriteria(TipoEjercicio.class);
		criteria.add(Restrictions.eq("idTipoEjercicio", id));
		return (TipoEjercicio) criteria.uniqueResult();
	}

	/**
	 * Metodo para encontrar un tipo de ejercicio por su nombre
	 * @param nombreEjercicio el nombre del ejercicio a buscar
	 * @return el tipo de ejercicio encontrado
	 */
	@Override
	public TipoEjercicio findByNombreEjercicio(String nombreEjercicio) {
		Criteria criteria = getSession().createCriteria(TipoEjercicio.class);
		criteria.add(Restrictions.eq("nombreEjercicio", nombreEjercicio));
		return (TipoEjercicio) criteria.uniqueResult();
	}

	/**
	 * Metodo para actualizar el tipo de ejercicio
	 * @param tipoEjercicio el tipo de ejercicio a actualizar
	 */
	@Override
	public void update(TipoEjercicio tipoEjercicio) {
		getSession().update(tipoEjercicio);
		
	}

	/**
	 * Metodo para borrar un tipo de ejercicio 
	 * @param tipoEjercicio el tipo de ejercicio a borrar
	 */
	@Override
	public void delete(TipoEjercicio tipoEjercicio) {
		getSession().delete(tipoEjercicio);
		
	}

}
