package com.sushmita.ork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrkApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrkApplication.class, args);
		System.out.println("initialized");
	}

}
