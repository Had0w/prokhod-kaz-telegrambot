package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {
    private SendBotMessageServiceImpl sendBotMessageService;
    public static final String NO_COMMAND_MESSAGE = "Данное сообщение не является командой";

    public NoCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId, NO_COMMAND_MESSAGE);
    }
}
