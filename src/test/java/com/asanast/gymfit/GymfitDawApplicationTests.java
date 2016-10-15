package com.asanast.gymfit;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		Usuario usuario = usuarioService.findById(5);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(sdf.format(usuario.getFechaRegistro()));
	}

}
