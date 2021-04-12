package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command{

    private SendBotMessageServiceImpl sendBotMessageService;
    public static final String UNKNOWN_COMMAND_MESSAGE = "Неизвестная команда";

    public UnknownCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId, UNKNOWN_COMMAND_MESSAGE);
    }
}
