package com.klyuev.prokhodkaztelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProkhodKazTelegrambotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProkhodKazTelegrambotApplication.class, args);
	}

}
