package com.asanast.gymfit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.asanast.gymfit.pojo.Peso;

public class PesoDaoImpl implements PesoDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Peso peso) {
		getSession().save(peso);
	}

	@Override
	public List<Peso> findAllByIdUser(int id) {
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
		
	}

	@Override
	public void delete(Peso peso) {
		getSession().delete(peso);
		
	}

}
