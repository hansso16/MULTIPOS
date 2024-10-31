package com.soses.multilines.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ThymeleafConfig {

    @Bean
    ThymeleafViewResolver viewResolver(MessageSource messageSource) {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine(messageSource));
	    viewResolver.setCharacterEncoding("UTF-8");
	    viewResolver.setViewNames(new String[] { ".html" });
	    viewResolver.setOrder(1);
	    return viewResolver;
	}

    @Bean
    @Description("Thymeleaf Template Resolver")
    SpringResourceTemplateResolver templateResolver() {
	    final SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setPrefix("classpath:/templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    return templateResolver;
	}

    @Bean
    SpringTemplateEngine templateEngine(MessageSource messageSource) {
	    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    templateEngine.setMessageSource(messageSource);
	    return templateEngine;
	}
}
