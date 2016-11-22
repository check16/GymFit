package com.asanast.gymfit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.PesoDao;
import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;

@Service
public class PesoService {
	
	@Autowired
	private PesoDao pesoDao;
	
	public void save(Peso peso) {
		pesoDao.save(peso);
	}
	
	public List<Peso> findAllByIdUsuario(Usuario usuario) {
		return pesoDao.findAllByIdUsuario(usuario.getIdUsuario());
	}
	
	public List<Peso> findAllByLastDay(Usuario usuario, int dias) {
		return pesoDao.findAllPesoByDias(usuario, dias);
	}
	
	public Peso findByUsuarioAndFecha(Usuario usuario, Date fecha) {
		return pesoDao.findByUsuarioAndFecha(usuario, fecha);
	}
	
	public List<Peso> findPesoUsuarioBetweenDates(Usuario usuario, Date fechaInicio, Date fechaFin) {
		return pesoDao.findBetweenDate(usuario, fechaInicio, fechaFin);
	}
	
	public void saveOrUpdate(Peso peso) {
		pesoDao.saveOrUpdate(peso);
	}
	
	public Peso findLastPeso(Usuario usuario) {
		return pesoDao.findByLastDate(usuario);
	}
	
	public void update(Peso peso) {
		pesoDao.update(peso);
	}
	
	public void delete(Peso peso) {
		pesoDao.delete(peso);
	}

}
