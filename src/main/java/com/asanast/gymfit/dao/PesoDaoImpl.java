package com.asanast.gymfit.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;


@Repository
@Transactional
public class PesoDaoImpl implements PesoDao{
	
	@Autowired
	
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional
	public void save(Peso peso) {
		getSession().save(peso);
		getSession().getTransaction().commit();
	}

	@Override
	@Transactional
	public List<Peso> findAllByIdUsuario(int id) {
		Criteria crit = getSession().createCriteria(Peso.class);
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.eq("usuario.idUsuario", id));
		final List<Peso> listaPesos = new ArrayList<>();
		for(final Object o : crit.list()) {
			listaPesos.add((Peso)o);
		}
		return listaPesos;
	}

	@Override
	@Transactional
	public void update(Peso peso) {
		getSession().update(peso);
		
	}

	@Override
	@Transactional
	public void delete(Peso peso) {
		getSession().delete(peso);
		
	}

	@Override
	@Transactional
	public Peso findByUsuarioAndFecha(Usuario usuario, Date fecha) {
		Criteria crit = getSession().createCriteria(Peso.class);
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.eq("usuario.idUsuario", usuario.getIdUsuario()));
		crit.add(Restrictions.eq("fecha", fecha));
		return (Peso) crit.uniqueResult();
	}

	@Override
	@Transactional
	public Peso findByLastDate(Usuario usuario) {
		Criteria crit = getSession().createCriteria(Peso.class);
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.eq("usuario.idUsuario", usuario.getIdUsuario()));
		crit.addOrder(Order.desc("fecha"));
		crit.setMaxResults(1);
		return (Peso) crit.uniqueResult();
	}

	@Override
	@Transactional
	public void saveOrUpdate(Peso peso) {
		getSession().saveOrUpdate(peso);
		
	}

	@Override
	public List<Peso> findAllPesoByDias(Usuario usuario, int dias) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -dias); 
		Criteria crit = getSession().createCriteria(Peso.class);
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.eq("usuario.idUsuario", usuario.getIdUsuario()));
		crit.add(Restrictions.ge("fecha", c.getTime()));
		final List<Peso> listaPesos = new ArrayList<>();
		for(final Object o : crit.list()) {
			listaPesos.add((Peso)o);
		}
		return listaPesos;
		
	}

	@Override
	public List<Peso> findBetweenDate(Usuario usuario, Date fechaInicio, Date fechaFinal) {
		Criteria crit = getSession().createCriteria(Peso.class);
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.eq("usuario.idUsuario", usuario.getIdUsuario()));
		crit.add(Restrictions.ge("fecha", fechaInicio));
		crit.add(Restrictions.le("fecha", fechaFinal));
		final List<Peso> listaPesos = new ArrayList<>();
		for(final Object o : crit.list()) {
			listaPesos.add((Peso)o);
		}
		return listaPesos;
	}

}
