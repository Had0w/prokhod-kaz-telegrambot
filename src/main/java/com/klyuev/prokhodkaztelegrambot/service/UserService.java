package com.klyuev.prokhodkaztelegrambot.service;
import com.klyuev.prokhodkaztelegrambot.entity.User;

import java.time.LocalTime;
import java.util.Optional;


public interface UserService {
    void addNewUser(User user);

    Optional<User> findByChatId(long chatId);

    boolean containsUser(long chatId);

    void updateStartOfWorkDay(long chatId, LocalTime localTime);

    void updateEndOfWorkDay(long chatId, LocalTime localTime);
}
