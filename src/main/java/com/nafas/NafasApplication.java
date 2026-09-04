package com.nafas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NafasApplication {

	public static void main(String[] args) {
		SpringApplication.run(NafasApplication.class, args);
	}

}
