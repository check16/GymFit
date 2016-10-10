package com.asanast.gymfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asanast.gymfit.dao.RolDao;
import com.asanast.gymfit.pojo.Rol;

@Service
public class RolService {

	@Autowired
	private RolDao rolDao;

	public void save(Rol rol) {
		rolDao.save(rol);
	}

	public List<Rol> findAll() {
		return rolDao.findAll();
	}

	public Rol findByNombreRol(String nombreRol) {
		return rolDao.findByNombreRol(nombreRol);
	}

	public void update(Rol rol) {
		rolDao.update(rol);
	}

	public void delete(Rol rol) {
		rolDao.delete(rol);
	}
}
