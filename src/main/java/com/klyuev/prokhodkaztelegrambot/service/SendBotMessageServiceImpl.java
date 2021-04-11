package com.klyuev.prokhodkaztelegrambot.service;

import com.klyuev.prokhodkaztelegrambot.bot.ProkhodKAZBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Implementation of {@link SendBotMessageService} interface.
 */

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private ProkhodKAZBot prokhodKAZBot;

    @Autowired
    public SendBotMessageServiceImpl(ProkhodKAZBot prokhodKAZBot) {
        this.prokhodKAZBot = prokhodKAZBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            prokhodKAZBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }
}
