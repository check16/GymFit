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

@Transactional
@Repository
public class RolDaoImpl implements RolDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Rol rol) {
		getSession().save(rol);
		
	}

	@Override
	public List<Rol> findAll() {
		Query query = getSession().createQuery("from Rol");
		final List<Rol> rols = new ArrayList<>();
		for(final Object o : query.list()) {
			rols.add((Rol)o);
		}
		return rols;
	}

	@Override
	public Rol findById(int id) {
		Criteria criteria = getSession().createCriteria(Rol.class);
		criteria.add(Restrictions.eq("idRol", id));
		return (Rol) criteria.uniqueResult();
	}

	@Override
	public List<Rol> findByNombreRol(String nombreRol) {
		final List<Rol> rols = new ArrayList<>();
		Criteria criteria = getSession().createCriteria(Rol.class);
		criteria.add(Restrictions.like("nombreRol", "%" + nombreRol + "%"));
		for(final Object o : criteria.list()) {
			rols.add((Rol)o);
		}
		return rols;
	}

	@Override
	public void update(Rol rol) {
		getSession().update(rol);
		
	}

	@Override
	public void delete(Rol rol) {
		getSession().delete(rol);
		
	}

}
