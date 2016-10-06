package com.asanast.gymfit;

import java.math.BigDecimal;
import java.sql.Date;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class GymfitDawApplicationTests {
	
	@Autowired
	private PesoDao pesoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private RolDao rolDao;

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
	@Test
	@Transactional
	public void contextLoads() throws ParseException {
		
		Usuario usuario2 = usuarioDao.findByLogin("pepito");
		
		Peso peso1 = pesoDao.findByUsuarioAndFecha(usuario2, new Date(format.parse("24-09-2016").getTime()));
		
		if(peso1!=null) {
			peso1.setPeso(new BigDecimal("75.7"));
			pesoDao.update(peso1);
			System.out.println("Peso ACTUALIZADO CORRECTAMENTE");
		}else {
			Peso peso2 = new Peso(new BigDecimal("60"), new Date(format.parse("24-09-2016").getTime()), usuario2);
			pesoDao.save(peso2);
		}
	}

}
