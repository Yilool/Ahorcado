package com.ahorcado.ahorcado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.*"})
public class AhorcadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhorcadoApplication.class, args);
	}

}
