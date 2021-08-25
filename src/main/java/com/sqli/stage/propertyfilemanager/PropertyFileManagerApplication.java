package com.sqli.stage.propertyfilemanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropertyFileManagerApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(PropertyFileManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
