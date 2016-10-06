package com.asanast.gymfit.dao;

import java.util.List;

import com.asanast.gymfit.pojo.Usuario;

public interface UsuarioDao {
	
	public void save(Usuario usuario);
	public List<Usuario> findAll();
	public Usuario findById(int id);
	public Usuario findByLogin(String login);
	public void update(Usuario usuario);
	public void delete(Usuario usuario);	
}
