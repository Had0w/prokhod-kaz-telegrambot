package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.time.LocalDateTime;

@Component
public class BalanceCommand implements Command {
    private final SendBotMessageServiceImpl sendBotMessageService;

    public BalanceCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, UserServiceImpl userService) {
        User user = userService.findByChatId(update.getMessage().getChatId());
        if(!user.getLastUpdate().toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
            user.setBalance(0);
            user.setCoeff(0.0);
            userService.setCoeff(user.getChatID(), user.getCoeff());
            userService.setBalance(user.getChatID(), user.getBalance());
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Остаток минут: " + user.getBalance());
        sendMessage.setChatId(String.valueOf(user.getChatID()));
        sendBotMessageService.sendMessage(sendMessage);
    }
}
