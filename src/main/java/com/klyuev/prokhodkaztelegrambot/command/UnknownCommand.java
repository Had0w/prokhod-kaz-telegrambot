package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UnknownCommand implements Command {
    private final SendBotMessageServiceImpl sendBotMessageService;

    public UnknownCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, UserServiceImpl userService) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText("Неизвестная команда");
        sendBotMessageService.sendMessage(sendMessage);
    }
}
