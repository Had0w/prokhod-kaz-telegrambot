package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.time.LocalDateTime;

@Component
public class StatisticCommand implements Command {
    private final SendBotMessageServiceImpl sendBotMessageService;

    public StatisticCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, UserServiceImpl userService) {
        int countOfUsers = userService.countAll();
        LocalDateTime lastUpdate = userService.selectLastUpdate();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText("Общее колличество пользователей: " + countOfUsers + "\n" +
                "Последнее обновление: " + lastUpdate);
        sendBotMessageService.sendMessage(sendMessage);
    }
}
