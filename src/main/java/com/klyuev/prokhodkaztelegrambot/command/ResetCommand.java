package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ResetCommand implements Command {
    private SendBotMessageServiceImpl sendBotMessageService;
    public static final String RESET_MESSAGE = "Все настройки были сброшены на дефолтные раб. день 8:00 - 17:00 (обед 12:00 - 13:00)";

    public ResetCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId, RESET_MESSAGE);
    }
}
