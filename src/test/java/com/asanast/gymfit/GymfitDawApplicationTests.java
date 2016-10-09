package com.asanast.gymfit;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.dao.PesoDao;
import com.asanast.gymfit.dao.RolDao;
import com.asanast.gymfit.dao.UsuarioDao;
import com.asanast.gymfit.pojo.Peso;
import com.asanast.gymfit.pojo.Usuario;
import com.asanast.gymfit.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GymfitDawApplicationTests {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private PesoDao pesoDao;

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
	@Test
	@Transactional
	public void contextLoads() throws ParseException {
		
		Usuario usuario = usuarioDao.findByLogin("pepito");
		
		Peso peso2 = pesoDao.findByUsuarioAndFecha(usuario, new Date(format.parse("24-09-2016").getTime()));
		
		if(peso2!=null) {
			peso2.setPeso(new BigDecimal("200.71"));
			pesoDao.update(peso2);
			System.out.println("Peso ACTUALIZADO CORRECTAMENTE");
		}else {
			Peso peso3 = new Peso(new BigDecimal("60"), new Date(format.parse("24-09-2016").getTime()), usuario);
			pesoDao.save(peso3);
			System.out.println("Peso GRABADO CORRECTAMENTE");
		}
	}

}
