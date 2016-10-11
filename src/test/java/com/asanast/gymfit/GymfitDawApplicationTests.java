package com.asanast.gymfit;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GymfitDawApplicationTests {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	@Transactional
	public void contextLoads() throws ParseException {
		
		Usuario usuario = new Usuario("juanito", "1234","juanito@prueba.es", 26, 170, "img/usuario-icono.png");
		
		usuarioService.save(usuario);
		
		System.out.println("ALMACENADO");
		
		
	}

}
