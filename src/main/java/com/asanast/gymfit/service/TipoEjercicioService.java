package com.asanast.gymfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.TipoEjercicioDao;
import com.asanast.gymfit.pojo.TipoEjercicio;

@Service
public class TipoEjercicioService {
	
	@Autowired
	private TipoEjercicioDao tipoEjercicioDao;

	public void save(TipoEjercicio tipoEjercicio) {
		tipoEjercicioDao.save(tipoEjercicio);
	}

	public List<TipoEjercicio> findAll() {
		return tipoEjercicioDao.findAll();
	}

	public TipoEjercicio findByNombreEjercicio(String nombreEjercicio) {
		return tipoEjercicioDao.findByNombreEjercicio(nombreEjercicio);
	}

	public void update(TipoEjercicio tipoEjercicio) {
		tipoEjercicioDao.update(tipoEjercicio);
	}

	public void delete(TipoEjercicio tipoEjercicio) {
		tipoEjercicioDao.delete(tipoEjercicio);
	}

}
