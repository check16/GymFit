package com.asanast.gymfit.dao;

import java.sql.Date;
import java.util.List;

import com.asanast.gymfit.pojo.Entrenamiento;

public interface EntrenamientoDao {
	
	public void save(Entrenamiento entrenamiento);
	public List<Entrenamiento> findAll();
	public List<Entrenamiento> findAllByIdUsuario(int id);
	public List<Entrenamiento> findAllBetweenDate(Entrenamiento entrenamiento, Date fechaInicio, Date fechaFin);
	public void update(Entrenamiento entrenamiento);
	public void delete(Entrenamiento entrenamiento);	

}
