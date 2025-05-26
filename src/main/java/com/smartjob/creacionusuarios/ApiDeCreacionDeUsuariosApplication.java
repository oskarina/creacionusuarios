package com.smartjob.creacionusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ApiDeCreacionDeUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDeCreacionDeUsuariosApplication.class, args);
	}

}
