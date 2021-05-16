package com.klyuev.prokhodkaztelegrambot.service;
import com.klyuev.prokhodkaztelegrambot.entity.User;

import java.util.Optional;


public interface UserService {
    void addNewUser(User user);

    Optional<User> findByChatId(long chatId);

    boolean containsUser(long chatId);
}
