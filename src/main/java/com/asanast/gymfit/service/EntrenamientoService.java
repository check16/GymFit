package com.asanast.gymfit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.EntrenamientoDao;
import com.asanast.gymfit.pojo.Entrenamiento;
import com.asanast.gymfit.pojo.Usuario;

@Service
public class EntrenamientoService {
	
	@Autowired
	private EntrenamientoDao entrenamientoDao;
	
	public void save(Entrenamiento entrenamiento, Usuario usuario) {
		entrenamiento.setUsuario(usuario);
		entrenamientoDao.save(entrenamiento);
	}
	
	public Entrenamiento findById(int id) {
		return entrenamientoDao.findById(id);
	}
	
	public List<Entrenamiento> findAll() {
		return entrenamientoDao.findAll();
	}
	
	public List<Entrenamiento> findAllByIdUsuario(int id) {
		return entrenamientoDao.findAllByIdUsuario(id);
		
	}
	
	public List<Entrenamiento> findFuerzaEjercicioUsuarioBetweenDates(Usuario usuario, Date fechaInicio, Date fechaFin, int idEjercicio) {
		return entrenamientoDao.findAllBetweenDateAndEjercicio(usuario, fechaInicio, fechaFin, idEjercicio);
	}
	
	public void saveOrUpdate(Entrenamiento entrenamiento, Usuario usuario) {
		if(entrenamiento.getIdEntrenamiento() == 0) {
			entrenamiento.setUsuario(usuario);
			entrenamientoDao.save(entrenamiento);
		}else {
			entrenamiento.setUsuario(usuario);
			entrenamientoDao.update(entrenamiento);
		}
	}
	
	public void update(Entrenamiento entrenamiento) {
		entrenamientoDao.update(entrenamiento);
	}
	
	public void delete(Entrenamiento entrenamiento) {
		entrenamientoDao.delete(entrenamiento);
	}

}
