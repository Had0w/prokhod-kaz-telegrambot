package com.klyuev.prokhodkaztelegrambot.service;

public interface SendBotMessageService {
    void sendMessage(Long chatId, String message);
}
