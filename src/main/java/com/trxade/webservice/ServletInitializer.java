package com.trxade.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ServletInitializer extends SpringBootServletInitializer {

	   public static void main(String[] args) {
	        SpringApplication.run(applicationClass, args);
	    }

	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(applicationClass);
	    }

	    private static Class<TrxadeWsApplication> applicationClass = TrxadeWsApplication.class;
	}
