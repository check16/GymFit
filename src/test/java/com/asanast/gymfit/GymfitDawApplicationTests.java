package com.asanast.gymfit;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.pojo.Ejercicio;
import com.asanast.gymfit.pojo.Entrenamiento;
import com.asanast.gymfit.pojo.TipoEjercicio;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.EntrenamientoService;
import com.asanast.gymfit.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GymfitDawApplicationTests {
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	@Transactional
	public void contextLoads() throws ParseException {
		
		Usuario usuario = usuarioService.findById(3);
		
		Entrenamiento entrenamiento = new Entrenamiento("Entreno 2", new Date(),"Notas" , usuario);
		ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
		TipoEjercicio tipoEjercicio = new TipoEjercicio("Dominadas", "Dominadas");
		listaEjercicios.add(new Ejercicio(new BigDecimal("45.9"), 30, entrenamiento, tipoEjercicio));
		entrenamiento.setEjercicios(listaEjercicios);
		entrenamientoService.save(entrenamiento, usuario);
		System.out.println("GRABADO");
	}

}
