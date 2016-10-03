package com.asanast.gymfit.dao;

import java.util.List;

import com.asanast.gymfit.pojo.Rol;

public interface RolDao {
	
	public void save(Rol rol);
	public List<Rol> findAll();
	public Rol findById(int id);
	public List<Rol> findByNombreRol(String nombreRol);
	public void update(Rol rol);
	public void delete(Rol rol);	

}
