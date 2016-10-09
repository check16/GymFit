package com.asanast.gymfit.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;

@Transactional
@Repository
public class PesoDaoImpl implements PesoDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Peso peso) {
		getSession().save(peso);
		getSession().getTransaction().commit();
	}

	@Override
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
	public void update(Peso peso) {
		getSession().update(peso);
		getSession().getTransaction().commit();
		
	}

	@Override
	public void delete(Peso peso) {
		getSession().delete(peso);
		
	}

	@Override
	public Peso findByUsuarioAndFecha(Usuario usuario, Date fecha) {
		Criteria crit = getSession().createCriteria(Peso.class);
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.eq("usuario.idUsuario", usuario.getIdUsuario()));
		crit.add(Restrictions.eq("fecha", fecha));
		return (Peso) crit.uniqueResult();
	}

}
