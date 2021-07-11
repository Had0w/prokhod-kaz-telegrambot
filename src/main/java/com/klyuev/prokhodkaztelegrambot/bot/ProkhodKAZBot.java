package com.klyuev.prokhodkaztelegrambot.bot;

import com.klyuev.prokhodkaztelegrambot.command.CommandContainer;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProkhodKAZBot extends TelegramLongPollingBot {
    private int balance;
    private UserServiceImpl userService;
    private final CommandContainer commandContainer;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public ProkhodKAZBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

    /**
     * Клавиатура
     */

    public static SendMessage sendEndInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton endButton1530 = new InlineKeyboardButton();
        InlineKeyboardButton endButton1545 = new InlineKeyboardButton();
        InlineKeyboardButton endButton1600 = new InlineKeyboardButton();
        InlineKeyboardButton endButton1615 = new InlineKeyboardButton();
        InlineKeyboardButton endButton1630 = new InlineKeyboardButton();
        InlineKeyboardButton endButton1700 = new InlineKeyboardButton();
        InlineKeyboardButton endButton1715 = new InlineKeyboardButton();
        InlineKeyboardButton endButton1730 = new InlineKeyboardButton();

        endButton1530.setText("15:30");
        endButton1530.setCallbackData(chatId + " end 15 31");
        endButton1545.setText("15:45");
        endButton1545.setCallbackData(chatId + " end 15 46");
        endButton1600.setText("16:00");
        endButton1600.setCallbackData(chatId + " end 16 1");
        endButton1615.setText("16:15");
        endButton1615.setCallbackData(chatId + " end 16 16");
        endButton1630.setText("16:30");
        endButton1630.setCallbackData(chatId + " end 16 31");
        endButton1700.setText("17:00");
        endButton1700.setCallbackData(chatId + " end 17 1");
        endButton1715.setText("17:15");
        endButton1715.setCallbackData(chatId + " end 17 16");
        endButton1730.setText("17:30");
        endButton1730.setCallbackData(chatId + " end 17 31");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(endButton1530);
        keyboardButtonsRow1.add(endButton1545);
        keyboardButtonsRow1.add(endButton1600);
        keyboardButtonsRow1.add(endButton1615);
        keyboardButtonsRow2.add(endButton1630);
        keyboardButtonsRow2.add(endButton1700);
        keyboardButtonsRow2.add(endButton1715);
        keyboardButtonsRow2.add(endButton1730);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText("Выберите время окончания Вашего рабочего дня");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();

        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                commandContainer.retrieveCommand(update.getMessage().getText()).execute(update, userService);
            }
        } else if (update.hasCallbackQuery()) {
            try {
                String[] data = update.getCallbackQuery().getData().split(" ");
                if (data[1].equals("start")) {
                    LocalTime localTime = LocalTime.of(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                    userService.updateStartOfWorkDay(Long.parseLong(data[0]), localTime);
                    execute(sendEndInlineKeyBoardMessage(Long.parseLong(data[0])));
                }
                if (data[1].equals("end")) {
                    LocalTime localTime = LocalTime.of(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                    userService.updateEndOfWorkDay(Long.parseLong(data[0]), localTime);
                    sendMessage.setText("Настройки изменены");
                    sendMessage.setChatId(data[0]);
                    execute(sendMessage);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

