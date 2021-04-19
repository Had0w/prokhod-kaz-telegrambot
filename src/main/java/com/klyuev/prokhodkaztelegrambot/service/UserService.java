package com.klyuev.prokhodkaztelegrambot.service;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {this.userRepository = userRepository;}

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public User findByChatId(long chatId) {
        return userRepository.findById(chatId).get();
    }
}
