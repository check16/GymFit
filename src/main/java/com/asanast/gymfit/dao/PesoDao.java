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
	//public List<Peso> findBetweenDate(Peso peso, Date fechaFinal);
	public void update(Peso peso);
	public void delete(Peso peso);
	public void saveOrUpdate(Peso peso);
}
