package com.klyuev.prokhodkaztelegrambot.service;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface UserService {

    int countAll();

    LocalDateTime selectLastUpdate();

    void saveUser(User user);

    User findByChatId(long chatId);

    boolean containsUser(long chatId);

    void updateStartOfWorkDay(long chatId, LocalTime localTime);

    void updateEndOfWorkDay(long chatId, LocalTime localTime);

    void setIsAtWork(Long chatId, Boolean bool);

    void setLastUpdate(Long chatId, LocalDateTime lastUpdate);

    void setCoeff(Long chatId, Double coeff);

    void setBalance(Long chatId, int balance);
}
