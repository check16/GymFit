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


@Repository
public class UsuarioDaoImpl implements UsuarioDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		getSession().save(usuario);	
	}

	@Override
	public List<Usuario> findAll() {
		Query query = getSession().createQuery("from Usuario");
		final List<Usuario> usuarios = new ArrayList<>();
		for(final Object o : query.list()) {
			usuarios.add((Usuario)o);
		}
		return usuarios;
	}

	@Override
	public Usuario findById(int id) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("idUsuario", id));
		return (Usuario) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public Usuario findByLogin(String login) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		return (Usuario) criteria.uniqueResult();
	}

	@Override
	public void update(Usuario usuario) {
		getSession().update(usuario);
		
	}

	@Override
	public void delete(Usuario usuario) {
		getSession().delete(usuario);
		
	}

}
