package com.sqli.stage.propertyfilemanager;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PropertyFileManagerApplication  {
	public static void main(String[] args) {
		SpringApplication.run(PropertyFileManagerApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().paths(PathSelectors
				.ant("/api/*")).build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Api CompreFileProperties", 
				"Api for Sqli Morocco Oujda", "1.0", "free to use"
				,new springfox.documentation.service.Contact("sqli oujda","https://www.sqli.com/Accueil/Groupe/Agences/SQLI-Maroc", " commercial-maroc@sqli.com")
				,"Api License"
				,"https://www.sqli.com/Accueil/Groupe/Agences/SQLI-Maroc", Collections.emptyList());

	}

	

}
