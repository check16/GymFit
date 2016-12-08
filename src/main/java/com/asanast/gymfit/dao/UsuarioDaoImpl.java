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

import com.asanast.gymfit.pojo.Usuario;

/**
 * Clase repositorio de implementacion del DAO del Tipo de ejercicio
 * @author antonio
 */
@Repository
public class UsuarioDaoImpl implements UsuarioDao{
	
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
	 * Metodo para guardar un usuario en la BD
	 * @param usuario el usuaruio a almacenar en la BD
	 */
	@Override
	@Transactional
	public void save(Usuario usuario) {
		getSession().save(usuario);	
	}

	/**
	 * Metodo para buscar todos los usuarios en la BD
	 * @return el conjunto de usuarios
	 */
	@Override
	@Transactional
	public List<Usuario> findAll() {
		Query query = getSession().createQuery("from Usuario");
		final List<Usuario> usuarios = new ArrayList<>();
		for(final Object o : query.list()) {
			usuarios.add((Usuario)o);
		}
		return usuarios;
	}

	/**
	 * Metodo para encontrar un usuario por su id
	 * @param id el id del usuario a encontrar
	 * @return el usuario encontrado
	 */
	@Override
	@Transactional
	public Usuario findById(int id) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("idUsuario", id));
		return (Usuario) criteria.uniqueResult();
	}

	/**
	 * Metodo para encontrar un usuario por su nombre de login
	 * @param login el login a buscar
	 * @return el usuario encontrado
	 */
	@Override
	@Transactional
	public Usuario findByLogin(String login) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		return (Usuario) criteria.uniqueResult();
	}

	/**
	 * Metodo para actualizar un usuario
	 * @param usuario el usuario a actualizar
	 */
	@Override
	@Transactional
	public void update(Usuario usuario) {
		getSession().update(usuario);
		
	}

	/**
	 * Metodo para borrar un usuario
	 * @param usuario el usuario a borrar
	 */
	@Override
	@Transactional
	public void delete(Usuario usuario) {
		getSession().delete(usuario);
		
	}

}
