package com.asanast.gymfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.RolDao;
import com.asanast.gymfit.dao.UsuarioDao;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.util.Constantes;

/**
 * Clase de servicio para el Usuario
 * @author antonio
 */
@Service
public class UsuarioService {
	
	/**
	 * Propiedad que encapsula el DAO del usuario
	 */
	@Autowired
	private UsuarioDao usuarioDao;
	
	/**
	 * Propiedad que encapsula el encoder de la contraseña
	 */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * Propiedad que encapsula el DAO del rol
	 */
	@Autowired
	private RolDao rolDao;
	
	/**
	 * Metodo para guardar un usuario
	 * @param usuario el usuario a guardar
	 */
	public void save(Usuario usuario) {
		usuario.setRol(rolDao.findById(Constantes.ROL_REGISTRADO));
		String claveUsr = usuario.getClave();
		usuario.setClave(passwordEncoder.encode(claveUsr));
		usuarioDao.save(usuario);
	}
	
	/**
	 * Metodo para buscar todos los usuarios
	 * @return el conjunto de usuarios
	 */
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}
	
	/**
	 * Metodo para buscar un usuario por su id
	 * @param id el id de usuario
	 * @return el usuario
	 */
	public Usuario findById(int id) {
		return usuarioDao.findById(id);
	}
	
	/**
	 * Metodo para buscar un usuario por su login
	 * @param login el login de usuario a buscar
	 * @return el usuario
	 */
	public Usuario findByLogin(String login) {
		return usuarioDao.findByLogin(login);
	}
	
	/**
	 * Metodo para actualizar un usuario
	 * @param usuario el usuario a actualizar
	 */
	public void update(Usuario usuario) {
		usuarioDao.update(usuario);
	}
	
	/**
	 * Metodo para borrar un usuario
	 * @param usuario el usuario a borrar
	 */
	public void delete(Usuario usuario) {
		usuarioDao.delete(usuario);
	}
}
