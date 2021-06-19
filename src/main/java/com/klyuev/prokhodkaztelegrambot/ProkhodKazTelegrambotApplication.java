package com.klyuev.prokhodkaztelegrambot;

import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProkhodKazTelegrambotApplication {
	static UserServiceImpl userService;
	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProkhodKazTelegrambotApplication.class, args);
	}

}
