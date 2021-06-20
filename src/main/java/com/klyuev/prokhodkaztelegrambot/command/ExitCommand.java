package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
При команде "Вход" программа проверяет на значене параметра isAtWork и когда был последний апдейт,
если не в текущий день, то операцуия выролняется
 */
@Component
public class ExitCommand implements Command {

    private final SendBotMessageServiceImpl sendBotMessageService;

    public ExitCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, UserServiceImpl userService) {
        String text;
        SendMessage sendMessage = new SendMessage();
        User user = userService.findByChatId(update.getMessage().getChatId());
        LocalDateTime now = LocalDateTime.now();
        sendMessage.setChatId(String.valueOf(user.getChatID()));
        if (user.getLastUpdate() != null && !user.getLastUpdate().toLocalDate().equals(now.toLocalDate())) {
            userService.setBalance(user.getChatID(), 0);
            userService.setCoeff(user.getChatID(), 0.0);
        }
        if (!user.isAtWork() && user.getLastUpdate() != null && user.getLastUpdate().toLocalDate().equals(now.toLocalDate())) {
            text = "Вы уже вышли";
        } else {
            userService.setIsAtWork(user.getChatID(), false);
            LocalDateTime lastUpdate = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
            userService.setLastUpdate(user.getChatID(), lastUpdate);

            text = "Вы вышли";
        }
        sendMessage.setText(text);
        sendBotMessageService.sendMessage(sendMessage);
    }
}
