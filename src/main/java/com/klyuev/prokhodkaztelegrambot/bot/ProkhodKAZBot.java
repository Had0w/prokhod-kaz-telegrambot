package com.klyuev.prokhodkaztelegrambot.bot;

import com.klyuev.prokhodkaztelegrambot.command.CommandContainer;
import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProkhodKAZBot extends TelegramLongPollingBot {

    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    private final String COMMAND_PREFIX = "/";
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

    }

//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage()) {
//            Message message = new Message();
//            SendMessage sendMessage = new SendMessage();
//            if (update.getMessage().hasText()) {
//                if(message.getText().equals("/start")) {
//                    message = update.getMessage();
//                    sendMessage = new SendMessage();
//                    if(!userService.containsUser(message.getChatId())) {
//                        sendMessage.setText("Добро пожаловать!");
//                        User user = new User(message.getChatId());
//                        userService.addNewUser(user);
//                    }
//                    else {
//                        User user = userService.findByChatId(message.getChatId()).get();
//                        sendMessage.setText("С возвращением! Начало вашего рабочего дня в " + user.getTimeStartWorkDay().getHour() + ":" +
//                                user.getTimeStartWorkDay().getMinute() + " конец в " + user.getTimeOfEndWorkDay().getHour() + ":" +
//                                user.getTimeOfEndWorkDay().getMinute());
//                    }
//                    sendMessage.setChatId(message.getChatId().toString());
//                    try {
//                        this.execute(sendMessage);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (update.getMessage().getText().equals("Настройка")) {
//                    try {
//                        execute(sendStartInlineKeyBoardMessage(update.getMessage().getChatId()));
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                }
//                else {
//                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
//                    sendMessage.setText(update.getMessage().getText());
//                    try {
//                        execute(sendReplyKeyboardMarkupMessage(update.getMessage().getChatId(), update.getMessage().getText()));
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        } else if (update.hasCallbackQuery()) {
//            try {
//                String[] data = update.getCallbackQuery().getData().split(" ");
//                if(data[1].equals("start")) {
//                    execute(sendEndInlineKeyBoardMessage(Long.parseLong(data[0])));
//                }
//                if(data[1].equals("end")) {
//
//                }
////            update.getMessage().getChatId(); // получим пользователя из бд
////            update.getCallbackQuery().getData(); // изменим начало рабочего дня
////                execute(sendReplyKeyboardMarkupMessage(update.getMessage().getChatId()));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static SendMessage sendStartInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("7:00");
        inlineKeyboardButton1.setCallbackData(chatId + " start 7 0");
        inlineKeyboardButton2.setText("7:30");
        inlineKeyboardButton2.setCallbackData(chatId + " start 7 30");
        inlineKeyboardButton3.setText("8:00");
        inlineKeyboardButton3.setCallbackData(chatId + " start 8 0");
        inlineKeyboardButton4.setText("8:30");
        inlineKeyboardButton4.setCallbackData(chatId + " start 8 30");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow2.add(inlineKeyboardButton3);
        keyboardButtonsRow2.add(inlineKeyboardButton4);
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

    public static SendMessage sendEndInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("16:00");
        inlineKeyboardButton1.setCallbackData(chatId + " end 16 0");
        inlineKeyboardButton2.setText("16:30");
        inlineKeyboardButton2.setCallbackData(chatId + " end 16 30");
        inlineKeyboardButton3.setText("17:00");
        inlineKeyboardButton3.setCallbackData(chatId + " end 17 0");
        inlineKeyboardButton4.setText("17:30");
        inlineKeyboardButton4.setCallbackData(chatId + " end 17 30");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow2.add(inlineKeyboardButton3);
        keyboardButtonsRow2.add(inlineKeyboardButton4);
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
        keyboardFirstButtons.add(new KeyboardButton("currentTime"));// создание двух кнопок на этой троке
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
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("/start")) {
                    Message message = update.getMessage();
                    SendMessage sendMessage = new SendMessage();
                    if (!userService.containsUser(message.getChatId())) {
                        sendMessage.setText("Добро пожаловать!");
                        User user = new User(message.getChatId());
                        userService.addNewUser(user);
                    } else {
                        User user = userService.findByChatId(message.getChatId()).get();
                        sendMessage.setText("С возвращением! Начало вашего рабочего дня в " + user.getTimeStartWorkDay().getHour() + ":" +
                                user.getTimeStartWorkDay().getMinute() + " конец в " + user.getTimeOfEndWorkDay().getHour() + ":" +
                                user.getTimeOfEndWorkDay().getMinute());
                    }
                    sendMessage.setChatId(message.getChatId().toString());
                    try {
                        this.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else if (update.getMessage().getText().equals("Настройка")) {
                    try {
                        execute(sendStartInlineKeyBoardMessage(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    sendMessage.setText("");
                    try {
                        execute(sendReplyKeyboardMarkupMessage(update.getMessage().getChatId(), update.getMessage().getText()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        else if (update.hasCallbackQuery()) {
            try {
                String[] data = update.getCallbackQuery().getData().split(" ");
                if(data[1].equals("start")) {
                    LocalTime localTime = LocalTime.of(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                    userService.updateStartOfWorkDay(Long.parseLong(data[0]), localTime);
                    execute(sendEndInlineKeyBoardMessage(Long.parseLong(data[0])));
                }
                if(data[1].equals("end")) {
                    LocalTime localTime = LocalTime.of(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                    userService.updateEndOfWorkDay(Long.parseLong(data[0]), localTime);
                }
//            update.getMessage().getChatId(); // получим пользователя из бд
//            update.getCallbackQuery().getData(); // изменим начало рабочего дня
//                execute(sendReplyKeyboardMarkupMessage(update.getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
            }
        }

