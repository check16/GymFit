package com.asanast.gymfit.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.pojo.Entrenamiento;
import com.asanast.gymfit.pojo.Usuario;

@Transactional
@Repository
public class EntrenamientoDaoImpl implements EntrenamientoDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Entrenamiento findById(int id) {
		Criteria criteria = getSession().createCriteria(Entrenamiento.class);
		criteria.add(Restrictions.eq("idEntrenamiento", id));
		
		return (Entrenamiento) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public void save(Entrenamiento entrenamiento) {
		getSession().save(entrenamiento);
		
	}

	@Override
	public List<Entrenamiento> findAll() {
		Query query = getSession().createQuery("from Entrenamiento");
		final List<Entrenamiento> entrenamientos = new ArrayList<>();
		for(final Object o : query.list()) {
			entrenamientos.add((Entrenamiento)o);
		}
		return entrenamientos;
	}

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

	@Override
	public List<Entrenamiento> findAllBetweenDate(Usuario usuario, Date fechaInicio, Date fechaFin) {
		// TODO crear metodo de EntrenamientoDAO para consultar entrenamientos entre fechas.
		return null;
	}

	@Override
	public void update(Entrenamiento entrenamiento) {
		getSession().saveOrUpdate(entrenamiento);
		
	}

	@Override
	public void delete(Entrenamiento entrenamiento) {
		getSession().delete(entrenamiento);
		
	}

	

}
