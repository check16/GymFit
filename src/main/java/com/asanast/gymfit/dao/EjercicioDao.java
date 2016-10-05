package com.asanast.gymfit.dao;

import java.util.List;

import com.asanast.gymfit.pojo.Ejercicio;

public interface EjercicioDao{
	
	public void save(Ejercicio ejercicio);
	public List<Ejercicio> findAllByIdEntrenamiento(int id);
	public void update(Ejercicio ejercicio);
	public void delete(Ejercicio ejercicio);

}
