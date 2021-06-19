package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * //                 * Стартовая команда, проверяет есть ли пользователь по данному chatId в базе, если нет, то создает нового
 */
@Component
public class StartCommand implements Command {

    private final SendBotMessageServiceImpl sendBotMessageService;

    public StartCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public SendMessage sendReplyKeyboardMarkupMessage(long chatId, String message) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>(); // список строк с кнопками
        KeyboardRow keyboardFirstButtons = new KeyboardRow(); // создание строки
        keyboardFirstButtons.add(new KeyboardButton("Вход"));
        keyboardFirstButtons.add(new KeyboardButton("Выход"));
        KeyboardRow keyboardButtons2 = new KeyboardRow();
        keyboardButtons2.add(new KeyboardButton("Настройка"));
        keyboardButtons2.add(new KeyboardButton("Справка"));
        keyboardRowList.add(keyboardFirstButtons);
        keyboardRowList.add(keyboardButtons2);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    @Override
    public void execute(Update update, UserServiceImpl userService) {
        Message message = update.getMessage();
        String text;
        if (!userService.containsUser(message.getChatId())) {
            text = "Добро пожаловать! По умолчанию начало рабочего дня в 8:00, конец в 17:00";
            User user = new User(message.getChatId());
            userService.addNewUser(user);
        } else {
            User user = userService.findByChatId(message.getChatId());
            LocalTime localTimeStart = user.getTimeStartWorkDay().plusMinutes(1);
            String lineStart = localTimeStart.format(DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime localTimeEnd = user.getTimeOfEndWorkDay().minusMinutes(1);
            String lineEnd = localTimeEnd.format(DateTimeFormatter.ofPattern("HH:mm"));
            text = "С возвращением! Начало вашего рабочего дня в " + lineStart + " конец в " + lineEnd;
        }
        sendBotMessageService.sendMessage(sendReplyKeyboardMarkupMessage(message.getChatId(), text));
    }
}
