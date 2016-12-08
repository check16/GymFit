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

/**
 * Clase repositorio de implementacion del DAO de Rol
 * @author antonio
 */
@Repository
public class RolDaoImpl implements RolDao{
	
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
	 * Metodo para guardar un rol en la BD
	 * @param rol el rol a guardar
	 */
	@Override
	public void save(Rol rol) {
		getSession().save(rol);
		getSession().getTransaction().commit();
		
	}

	/**
	 * Metodo para obtener todos los roles de la BD
	 * @return el conjunto de roles
	 */
	@Override
	@Transactional
	public List<Rol> findAll() {
		Query query = getSession().createQuery("from Rol");
		final List<Rol> rols = new ArrayList<>();
		for(final Object o : query.list()) {
			rols.add((Rol)o);
		}
		return rols;
	}

	/**
	 * Metodo para encontrar un rol por su id
	 * @param id el id del rol
	 * @return el rol
	 */
	@Override
	@Transactional
	public Rol findById(int id) {
		Criteria criteria = getSession().createCriteria(Rol.class);
		criteria.add(Restrictions.eq("idRol", id));
		return (Rol) criteria.uniqueResult();
	}

	/**
	 * Metodo para encontrar un rol por su nombre
	 * @param nombreRol el nombre del rol
	 * @return el rol
	 */
	@Override
	@Transactional
	public Rol findByNombreRol(String nombreRol) {
		Criteria criteria = getSession().createCriteria(Rol.class);
		criteria.add(Restrictions.eq("nombreRol", nombreRol));
		return (Rol) criteria.uniqueResult();
	}

	/**
	 * Metodo para actualizar el rol
	 * @param rol el rol a actualizar
	 */
	@Override
	@Transactional
	public void update(Rol rol) {
		getSession().update(rol);
		
	}

	/**
	 * Metodo para borrar un rol
	 * @param rol el rol a borrar
	 */
	@Override
	@Transactional
	public void delete(Rol rol) {
		getSession().delete(rol);
		
	}

}
