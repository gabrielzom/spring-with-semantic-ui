package br.org.recode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication (exclude = {SecurityAutoConfiguration.class })
public class CrudApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(CrudApplication.class, args);
	}

}