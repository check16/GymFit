package com.asanast.gymfit.dao;

import java.util.Date;
import java.util.List;

import com.asanast.gymfit.pojo.Entrenamiento;
import com.asanast.gymfit.pojo.Usuario;

public interface EntrenamientoDao {
	
	public void save(Entrenamiento entrenamiento);
	public Entrenamiento findById(int id);
	public List<Entrenamiento> findAll();
	public List<Entrenamiento> findAllByIdUsuario(int id);
	public List<Entrenamiento> findAllBetweenDateAndEjercicio(Usuario usuario, Date fechaInicio, Date fechaFin, int idEjercicio);
	public void update(Entrenamiento entrenamiento);
	public void delete(Entrenamiento entrenamiento);	

}
