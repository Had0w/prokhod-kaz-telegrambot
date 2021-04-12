package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command{
    private SendBotMessageServiceImpl sendBotMessageService;

    public static final String START_COMMAND_MESSAGE = "Добро пожаловать в телеграмм бот для определения коэффициента опоздания," +
            "по умолчанию рабочий день выставлен с 8:00 до 17:00 (обед с 12:00 до 13:00), можно изменить в настройках";
    /**
     *
     *  to avoid circle dependency we don't user @Autowired  for @param sendBotMessageService
     */
    public StartCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId, START_COMMAND_MESSAGE);
    }
}
