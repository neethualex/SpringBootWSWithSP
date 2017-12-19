package com.trxade.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication
//
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableOAuth2Client
@EnableAsync
@SpringBootApplication
@EnableSwagger2
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TrxadeWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrxadeWsApplication.class, args);
	}
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.trxade.webservice.controller"))
                .paths(regex("/trxadeServices.*"))
                .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Trxade REST API",
                "REST API for Trxade Web Service",
                "1.0",
                "Terms of service",
                new Contact("Alex Jeyasingh", "https://github.com/neethualex", "alexjeyasingh@gmail.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
