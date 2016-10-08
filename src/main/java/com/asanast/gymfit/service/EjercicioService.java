package com.asanast.gymfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.EjercicioDao;
import com.asanast.gymfit.pojo.Ejercicio;
import com.asanast.gymfit.pojo.TipoEjercicio;

@Service
public class EjercicioService {
	
	@Autowired
	private EjercicioDao ejercicioDao;
	
	public void save(Ejercicio ejercicio, TipoEjercicio tipoEjercicio) {
		ejercicio.setTipoEjercicio(tipoEjercicio);
		ejercicioDao.save(ejercicio);
	}
	
	public List<Ejercicio> findAllByIdEntrenamiento(int id) {
		return ejercicioDao.findAllByIdEntrenamiento(id);
		
	}
	
	public void saveOrUpdate(Ejercicio ejercicio, TipoEjercicio tipoEjercicio) {
		if(ejercicio.getIdEjercicio() == 0) {
			ejercicio.setTipoEjercicio(tipoEjercicio);
			ejercicioDao.save(ejercicio);
		}else {
			ejercicio.setTipoEjercicio(tipoEjercicio);
			ejercicioDao.update(ejercicio);
		}
	}
	
	public void update(Ejercicio ejercicio) {
		ejercicioDao.update(ejercicio);
	}
	
	public void delete(Ejercicio ejercicio) {
		ejercicioDao.delete(ejercicio);
	}

}