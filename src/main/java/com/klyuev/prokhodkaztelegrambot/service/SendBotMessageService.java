package com.klyuev.prokhodkaztelegrambot.service;
import org.springframework.stereotype.Service;

/**
 * Service for sending messages via telegram-bot.
 */

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
}
