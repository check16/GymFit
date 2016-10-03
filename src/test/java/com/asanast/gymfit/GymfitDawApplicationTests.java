package com.asanast.gymfit;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.dao.UsuarioDao;
import com.asanast.gymfit.pojo.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GymfitDawApplicationTests {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Test
	@Transactional
	public void contextLoads() {
		List<Usuario> usuarios = usuarioDao.findAll();
		for(Usuario usuario : usuarios) {
			System.out.println(usuario.getRol().getDescRol());
		}
		
	}

}
