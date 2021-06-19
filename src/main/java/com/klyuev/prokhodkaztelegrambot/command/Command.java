package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    void execute(Update update, UserServiceImpl userService);
}
