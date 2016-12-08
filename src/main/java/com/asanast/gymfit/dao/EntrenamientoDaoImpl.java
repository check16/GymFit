package com.asanast.gymfit.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.pojo.Entrenamiento;
import com.asanast.gymfit.pojo.Usuario;

/**
 * Clase repositorio de implementacion del DAO de Entrenamiento
 * @author antonio
 */
@Transactional
@Repository
public class EntrenamientoDaoImpl implements EntrenamientoDao{
	
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
	 * Metodo para encontrar un entrenamiento por su id
	 * @param id el id del entrenamiento
	 * @return el entrenamiento
	 */
	@Override
	public Entrenamiento findById(int id) {
		Criteria criteria = getSession().createCriteria(Entrenamiento.class);
		criteria.add(Restrictions.eq("idEntrenamiento", id));
		
		return (Entrenamiento) criteria.uniqueResult();
	}

	/**
	 * Metodo para guardar un entrenamiento en la BD
	 * @param entrenamiento el entrenamiento a guardar
	 */
	@Override
	@Transactional
	public void save(Entrenamiento entrenamiento) {
		getSession().save(entrenamiento);
		
	}

	/**
	 * Metodo para encontrar todos los entrenamientos
	 * @return la lista de todos los entrenamientos
	 */
	@Override
	public List<Entrenamiento> findAll() {
		Query query = getSession().createQuery("from Entrenamiento");
		final List<Entrenamiento> entrenamientos = new ArrayList<>();
		for(final Object o : query.list()) {
			entrenamientos.add((Entrenamiento)o);
		}
		return entrenamientos;
	}

	/**
	 * Metodo para encontrar los entrenamientos de un determinado id de usuario
	 * @param id el id de usuario
	 * @return la lista de los entrenamientos del usuario
	 */
	@Override
	public List<Entrenamiento> findAllByIdUsuario(int id) {
		Criteria crit = getSession().createCriteria(Entrenamiento.class);
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.eq("usuario.idUsuario", id)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		final List<Entrenamiento> entrenamientos = new ArrayList<>();
		for(final Object o : crit.list()) {
			entrenamientos.add((Entrenamiento)o);
		}
		return entrenamientos;
	}

	/**
	 * Metodo para actualizar un entrenamiento
	 * @param entrenamiento el entrenamiento a actualizar
	 */
	@Override
	public void update(Entrenamiento entrenamiento) {
		getSession().saveOrUpdate(entrenamiento);
		
	}

	/**
	 * Metodo para borrar un entrenamiento
	 * @param entrenamiento el entrenamiento a borrar
	 */
	@Override
	public void delete(Entrenamiento entrenamiento) {
		getSession().delete(entrenamiento);
		
	}

	/**
	 * Metodo para encontrar los entrenamientos a partir de un rango de fechas y que tengan un determinado ejercicio
	 * @param usuario el usuario
	 * @param fechaInicio la fecha de inicio de los entrenamientos a buscar
	 * @param fechaFin la fecha de fin de los entrenamientos a buscar
	 * @param idEjercicio el id del ejercicio del entrenamiento
	 * @return la lista de los entrenamientos
	 */
	@Override
	public List<Entrenamiento> findAllBetweenDateAndEjercicio(Usuario usuario, Date fechaInicio, Date fechaFin,
			int idEjercicio) {
		Criteria crit = getSession().createCriteria(Entrenamiento.class);
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.eq("usuario.idUsuario", usuario.getIdUsuario()));
		crit.add(Restrictions.ge("fecha", fechaInicio));
		crit.add(Restrictions.le("fecha", fechaFin));
		crit.createAlias("ejercicios.tipoEjercicio", "ejercicioTipo");
		crit.add(Restrictions.eq("ejercicioTipo.idTipoEjercicio", idEjercicio));
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("fecha"));
		final List<Entrenamiento> entrenamientos = new ArrayList<>();
		for(final Object o : crit.list()) {
			entrenamientos.add((Entrenamiento)o);
		}
		return entrenamientos;
	}

	

}
