package com.asanast.gymfit;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.asanast.gymfit.pojo.TipoEjercicio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GymfitDawApplicationTests {

	@Autowired
	private SessionFactory sf;
	
	@Test
	@Transactional
	public void contextLoads() {
		sf.getCurrentSession().save(new TipoEjercicio("Prueba2", "Prueba2"));
		sf.getCurrentSession().getTransaction().commit();
		System.out.println("GRABADO");
	}

}
