package com.asanast.gymfit.dao;

import java.util.List;

import com.asanast.gymfit.pojo.TipoEjercicio;

public interface TipoEjercicioDao {
	
	public void save(TipoEjercicio tipoEjercicio);
	public List<TipoEjercicio> findAll();
	public TipoEjercicio findById(int id);
	public TipoEjercicio findByNombreEjercicio(String nombreEjercicio);
	public void update(TipoEjercicio tipoEjercicio);
	public void delete(TipoEjercicio tipoEjercicio);

}
