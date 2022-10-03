package com.example.onliner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.onliner")
public class OnlinerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinerApplication.class, args);
	}

}
