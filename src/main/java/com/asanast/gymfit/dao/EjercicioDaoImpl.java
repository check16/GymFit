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

@Transactional
@Repository
public class EjercicioDaoImpl implements EjercicioDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Ejercicio ejercicio) {
		getSession().save(ejercicio);
		getSession().getTransaction().commit();
		
	}

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

	@Override
	public void update(Ejercicio ejercicio) {
		getSession().update(ejercicio);
		
	}

	@Override
	public void delete(Ejercicio ejercicio) {
		getSession().delete(ejercicio);
		
	}

}
