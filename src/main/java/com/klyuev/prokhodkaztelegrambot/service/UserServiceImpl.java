package com.klyuev.prokhodkaztelegrambot.service;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addNewUser(User user) {
        userRepository.save(user);
    }
    @Override
    public Optional<User> findByChatId(long chatId) {
        return userRepository.findById(chatId);
    }
    @Override
    public boolean containsUser(long chatId) {
        return userRepository.existsById(chatId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
