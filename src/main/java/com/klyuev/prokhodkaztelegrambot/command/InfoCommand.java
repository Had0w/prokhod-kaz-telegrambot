package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class InfoCommand implements Command {
    private final SendBotMessageServiceImpl sendBotMessageService;

    public InfoCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, UserServiceImpl userService) {
        User user = userService.findByChatId(update.getMessage().getChatId());
        String text = "- Данный бот является информационным и не связан с проходной системой предприятия\n" +
                "-При нажатии кнопки \"Вход\"  производится расчет коэфициена опоздания, нажмите данную кнопку при использовании " +
                "контрольного устройства на ВАШЕМ рабочем месте, не на проходной!\n " +
                "-При уходе с рабочего места в течении дня используйте данный бот за пять минут ДО и ПОСЛЕ прохода через основную проходную\n" +
                "-Вы можете настроить начало и конец вашего рабочего дня";
        sendBotMessageService.sendMessage(user.getChatID(), text);
    }
}
