package com.asanast.gymfit.dao;

import java.util.Date;
import java.util.List;

import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;

public interface PesoDao {
	
	public void save(Peso peso);
	public List<Peso> findAllByIdUsuario(int id);
	public Peso findByUsuarioAndFecha(Usuario usuario, Date fecha);
	public Peso findByLastDate(Usuario usuario);
	public List<Peso> findAllPesoByDias(Usuario usuario, int dias);
	public List<Peso> findBetweenDate(Usuario usuario, Date fechaInicio, Date fechaFinal);
	public Double findAvgPesoByDias(Usuario usuario, int dias);
	public void update(Peso peso);
	public void delete(Peso peso);
	public void saveOrUpdate(Peso peso);
}
