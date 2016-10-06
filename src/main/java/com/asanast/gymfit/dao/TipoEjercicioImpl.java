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

import com.asanast.gymfit.pojo.Rol;
import com.asanast.gymfit.pojo.TipoEjercicio;

@Transactional
@Repository
public class TipoEjercicioImpl implements TipoEjercicioDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(TipoEjercicio tipoEjercicio) {
		getSession().save(tipoEjercicio);
		getSession().getTransaction().commit();
		
	}

	@Override
	public List<TipoEjercicio> findAll() {
		Query query = getSession().createQuery("from TipoEjercicio");
		final List<TipoEjercicio> tipoEjercicios = new ArrayList<>();
		for(final Object o : query.list()) {
			tipoEjercicios.add((TipoEjercicio)o);
		}
		return tipoEjercicios;
	}

	@Override
	public TipoEjercicio findById(int id) {
		Criteria criteria = getSession().createCriteria(TipoEjercicio.class);
		criteria.add(Restrictions.eq("idTipoEjercicio", id));
		return (TipoEjercicio) criteria.uniqueResult();
	}

	@Override
	public List<TipoEjercicio> findByNombreEjercicio(String nombreEjercicio) {
		final List<TipoEjercicio> tipoEjercicios = new ArrayList<>();
		Criteria criteria = getSession().createCriteria(Rol.class);
		criteria.add(Restrictions.like("nombreEjercicio", "%" + nombreEjercicio + "%"));
		for(final Object o : criteria.list()) {
			tipoEjercicios.add((TipoEjercicio)o);
		}
		return tipoEjercicios;
	}

	@Override
	public void update(TipoEjercicio tipoEjercicio) {
		getSession().update(tipoEjercicio);
		
	}

	@Override
	public void delete(TipoEjercicio tipoEjercicio) {
		getSession().delete(tipoEjercicio);
		
	}

}
