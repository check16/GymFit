package com.asanast.gymfit.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfig {

	@Autowired
	private SpringTemplateEngine templateEngine;

	@PostConstruct
	public void init() {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

		resolver.setPrefix("templates/vistas/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("LEGACYHTML5");
		resolver.setOrder(templateEngine.getTemplateResolvers().size());

		templateEngine.addTemplateResolver(resolver);
	}

}
