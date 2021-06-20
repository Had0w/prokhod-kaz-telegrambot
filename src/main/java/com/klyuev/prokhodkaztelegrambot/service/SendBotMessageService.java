package com.klyuev.prokhodkaztelegrambot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface SendBotMessageService {
    void sendMessage(SendMessage sendMessage);
}
