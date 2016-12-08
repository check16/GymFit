package com.asanast.gymfit.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;

/**
 * Clase repositorio de implementacion del DAO del Peso
 * @author antonio
 */
@Repository
@Transactional
public class PesoDaoImpl implements PesoDao{
	
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
	 * Metodo para guardar un peso en la BD
	 * @param peso el peso a guardar
	 */
	@Override
	@Transactional
	public void save(Peso peso) {
		getSession().save(peso);
		getSession().getTransaction().commit();
	}

	/**
	 * Metodo para encontrar los pesos de un id de usuario
	 * @param id el id de usuario
	 * @return el conjunto de pesos del usuario
	 */
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

	/**
	 * Metodo para actualizar el peso
	 * @param peso el peso a actualizar
	 */
	@Override
	@Transactional
	public void update(Peso peso) {
		getSession().update(peso);
		
	}

	/**
	 * Metodo para borrar el peso
	 * @param peso el peso a borrar
	 */
	@Override
	@Transactional
	public void delete(Peso peso) {
		getSession().delete(peso);
		
	}

	/**
	 * Metodo para encontrar el peso de un usuario en una fecha
	 * @param usuario el usuario a encontrar su peso
	 * @param fecha la fecha del peso
	 * @return el peso encontrado
	 */
	@Override
	@Transactional
	public Peso findByUsuarioAndFecha(Usuario usuario, Date fecha) {
		Criteria crit = getSession().createCriteria(Peso.class);
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.eq("usuario.idUsuario", usuario.getIdUsuario()));
		crit.add(Restrictions.eq("fecha", fecha));
		return (Peso) crit.uniqueResult();
	}

	/**
	 * Metodo para encontrar el ultimo peso registrado de un determinado usuario
	 * @param usuario el usuario a encontrar el ultimo peso
	 * @return el peso encontrado
	 */
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

	/**
	 * Metodo para actualizar o guardar un peso
	 * @param peso el peso a actualizar o guardar
	 */
	@Override
	@Transactional
	public void saveOrUpdate(Peso peso) {
		getSession().saveOrUpdate(peso);
		
	}

	/**
	 * Metodo para encontrar los pesos X dias atrás del dia actual
	 * @param usuario el usuario a encontrar los pesos
	 * @param dias numero de dias atrás a buscar los pesos
	 * @return el conjunto de pesos encontrado para el usuario
	 */
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

	/**
	 * Metodo para encontrar los pesos en un rango de fechas para un determinado usuario
	 * @param usuario el usuario a encontrar los pesos
	 * @param fechaInicio fecha de inicio
	 * @param fechaFinal fecha de fin
	 * @return el conjunto de pesos encontrado en ese rango de fechas y usuario
	 */
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

	/**
	 * Metodo para obtener el promedio del peso de un usuario X dias atrás del día actual
	 * @param usuario el usuario a encontrar los pesos
	 * @param dias numero de dias atrás a buscar los pesos
	 * @return el valor del promedio del peso
	 */
	@Override
	public Double findAvgPesoByDias(Usuario usuario, int dias) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -dias); 
		Criteria crit = getSession().createCriteria(Peso.class);
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.eq("usuario.idUsuario", usuario.getIdUsuario()));
		crit.add(Restrictions.ge("fecha", c.getTime()));
		crit.setProjection(Projections.avg("pesoReg"));
		Double avgPeso = (Double) crit.uniqueResult();
		return avgPeso;
	}

}
