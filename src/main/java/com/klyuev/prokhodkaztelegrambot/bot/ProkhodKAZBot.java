package com.klyuev.prokhodkaztelegrambot.bot;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ProkhodKAZBot extends TelegramLongPollingBot {
    private UserServiceImpl userService;
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    private final String COMMAND_PREFIX = "/";
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;
    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public ProkhodKAZBot() {

    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            if(!userService.containsUser(message.getChatId())) {
                sendMessage.setText("Добро пожаловать!");
                User user = new User(message.getChatId());
                userService.addNewUser(user);
            }
            else {
                sendMessage.setText("С возвращением!");
            }
            sendMessage.setChatId(message.getChatId().toString());
            this.execute(sendMessage);
        }
    }
}
