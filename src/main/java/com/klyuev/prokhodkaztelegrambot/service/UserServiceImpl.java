package com.klyuev.prokhodkaztelegrambot.service;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class UserServiceImpl implements UserService{
    private  UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int countAll() {
        return userRepository.countAll();
    }

    @Override
    public LocalDateTime selectLastUpdate() {
        return userRepository.selectLastUpdate();
    }

    @Override
    public void addNewUser(User user) {
        userRepository.save(user);
    }
    @Override
    public User findByChatId(long chatId) {
        return userRepository.findById(chatId).get();
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
}
