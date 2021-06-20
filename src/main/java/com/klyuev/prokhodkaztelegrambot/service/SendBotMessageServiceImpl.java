package com.klyuev.prokhodkaztelegrambot.service;
import com.klyuev.prokhodkaztelegrambot.bot.ProkhodKAZBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final ProkhodKAZBot prokhodKAZBot;

    @Autowired
    public SendBotMessageServiceImpl(ProkhodKAZBot prokhodKAZBot) {
        this.prokhodKAZBot = prokhodKAZBot;
    }

    @Override
    public void sendMessage(SendMessage sendMessage) {
        try {
            prokhodKAZBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
