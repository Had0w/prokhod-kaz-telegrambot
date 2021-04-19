package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command{
    private SendBotMessageServiceImpl sendBotMessageService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    public static final String NEW_START_COMMAND_MESSAGE = "Добро пожаловать в телеграмм бот для определения коэффициента опоздания," +
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
        User user = userService.findByChatId(update.getMessage().getChatId());
        if(user == null) { sendBotMessageService.sendMessage(chatId, NEW_START_COMMAND_MESSAGE); }
        else {
            String message = String.format("С возвращением! Ваш рабочий день начинается в %d до %d (обед %d) изменить можно в настройках"
            , user.getTimeStartWorkDay(), user.getTimeOfEndWorkDay(), user.getTimeOfLunch());
            sendBotMessageService.sendMessage(chatId, message);
        }
    }
}
