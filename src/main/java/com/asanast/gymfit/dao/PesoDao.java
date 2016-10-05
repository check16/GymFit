package com.asanast.gymfit.dao;

import java.util.List;

import com.asanast.gymfit.pojo.Peso;

public interface PesoDao {
	
	public void save(Peso peso);
	public List<Peso> findAllByIdUsuario(int id);
	//public List<Peso> findBetweenDate(Peso peso, Date fechaFinal);
	public void update(Peso peso);
	public void delete(Peso peso);
}
