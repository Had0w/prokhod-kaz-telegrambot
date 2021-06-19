package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class SettingCommand implements Command {
    private final SendBotMessageServiceImpl sendBotMessageService;

    public SettingCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public static SendMessage sendStartInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton startButton630 = new InlineKeyboardButton();
        InlineKeyboardButton startButton645 = new InlineKeyboardButton();
        InlineKeyboardButton startButton700 = new InlineKeyboardButton();
        InlineKeyboardButton startButton715 = new InlineKeyboardButton();
        InlineKeyboardButton startButton730 = new InlineKeyboardButton();
        InlineKeyboardButton startButton800 = new InlineKeyboardButton();
        InlineKeyboardButton startButton815 = new InlineKeyboardButton();
        InlineKeyboardButton startButton830 = new InlineKeyboardButton();

        startButton630.setText("6:30");
        startButton630.setCallbackData(chatId + " start 6 29");
        startButton645.setText("6:45");
        startButton645.setCallbackData(chatId + " start 6 44");
        startButton700.setText("7:00");
        startButton700.setCallbackData(chatId + " start 6 59");
        startButton715.setText("7:15");
        startButton715.setCallbackData(chatId + " start 7 15");
        startButton730.setText("7:30");
        startButton730.setCallbackData(chatId + " start 7 29");
        startButton800.setText("8:00");
        startButton800.setCallbackData(chatId + " start 7 59");
        startButton815.setText("8:15");
        startButton815.setCallbackData(chatId + " start 8 16");
        startButton830.setText("8:30");
        startButton830.setCallbackData(chatId + " start 8 29");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(startButton630);
        keyboardButtonsRow1.add(startButton645);
        keyboardButtonsRow1.add(startButton700);
        keyboardButtonsRow1.add(startButton715);
        keyboardButtonsRow2.add(startButton730);
        keyboardButtonsRow2.add(startButton800);
        keyboardButtonsRow2.add(startButton815);
        keyboardButtonsRow2.add(startButton830);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText("Выберите время начала Вашего рабочего дня");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    /**
     * //                 * Запускает настройку параметров пользователя
     * //
     */
    @Override
    public void execute(Update update, UserServiceImpl userService) {
        User user = userService.findByChatId(update.getMessage().getChatId());
        sendBotMessageService.sendMessage(sendStartInlineKeyBoardMessage(user.getChatID()));
    }
}

