package com.klyuev.prokhodkaztelegrambot.prototype;

import com.klyuev.prokhodkaztelegrambot.entity.User;

import java.time.LocalTime;

public class UserPrototype {
    public static User createUserPrototype(long id) {
        User user = new User(id);
        user.setTimeStartWorkDay(LocalTime.of(7, 29));
        user.setTimeOfEndWorkDay(LocalTime.of(16, 31));
        user.setTimeOfLunch(LocalTime.of(12, 1));
        return user;
    }
}
