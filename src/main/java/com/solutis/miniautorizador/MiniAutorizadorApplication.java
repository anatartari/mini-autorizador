package com.solutis.miniautorizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.solutis.miniautorizador.service")
@ComponentScan("com.solutis.miniautorizador.controller")
@ComponentScan("com.solutis.miniautorizador.exception")
public class MiniAutorizadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniAutorizadorApplication.class, args);
	}

}
