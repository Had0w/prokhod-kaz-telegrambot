package com.klyuev.prokhodkaztelegrambot;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.UserService;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.time.LocalTime;
import java.util.List;

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
//		userService.updateStartOfWorkDay(786831575, LocalTime.of(8, 00));
	}

}
