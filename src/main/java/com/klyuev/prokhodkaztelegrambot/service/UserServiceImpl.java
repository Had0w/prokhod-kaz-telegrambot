package com.klyuev.prokhodkaztelegrambot.service;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Override
    public void updateStartOfWorkDay(long chatId, LocalTime localTime) {
        userRepository.updateStartOfWorkDay(chatId, localTime);
    }

    @Override
    public void updateEndOfWorkDay(long chatId, LocalTime localTime) {
        userRepository.updateEndOfWorkDay(chatId, localTime);
    }

    @Override
    public void setIsAtWork(Long chatId, Boolean bool) {
        userRepository.setIsAtWork(chatId, bool);
    }

    @Override
    public void setLastUpdate(Long chatId, LocalDateTime lastUpdate) {
        userRepository.setLastUpdate(chatId, lastUpdate);
    }

    @Override
    public void setCoeff(Long chatId, Double coeff) {
        userRepository.setCoeff(chatId, coeff);
    }

    @Override
    public void setBalance(Long chatId, int balance) {
        userRepository.setBalance(chatId, balance);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
