package com.klyuev.prokhodkaztelegrambot.bot;

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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProkhodKAZBot extends TelegramLongPollingBot {

    private UserServiceImpl userService;

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

    }
    /**
     Клавиатуры
     */
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
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("/start")) {
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
                        this.execute(sendReplyKeyboardMarkupMessage(Long.parseLong(sendMessage.getChatId()), sendMessage.getText()));

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
                /**
                    При команде "Вход" программа проверяет на значене параметра isAtWork и когда был последний апдейт,
                 если не в текущий день, то операцуия выролняется
                 */
                else if (update.getMessage().getText().equals("Вход")) {
                    User user = userService.findByChatId(update.getMessage().getChatId()).get();
                    LocalTime now = LocalTime.now();
                    if(user.isAtWork() && user.getLastUpdate() != null &&
                            (user.getLastUpdate().getDayOfMonth() == LocalDate.now().getDayOfMonth() &&
                    user.getLastUpdate().getDayOfWeek() == LocalDate.now().getDayOfWeek())) {
                        sendMessage.setText("Вы уже на работе");
                    }
                    else if(user.getTimeOfEndWorkDay().isBefore(now)) {
                        sendMessage.setText("Ваш рабочий день уже закончился");
                    }
                    else {
                        userService.setIsAtWork(user.getChatID(), true);
                        if(user.getTimeStartWorkDay().isBefore(now)) {
                            double coeff = late(user);
                            sendMessage.setText("Вы вошли, ваше опоздание " + coeff);
                        }
                        else {
                            sendMessage.setText("Вы вошли и не опоздали");
                        }
                        sendMessage.setChatId(String.valueOf(user.getChatID()));
                    }
                    try {
                        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                        this.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                /**
                 При команде "Вход" программа проверяет на значене параметра isAtWork и когда был последний апдейт,
                 если не в текущий день, то операцуия выролняется
                 */
                else if (update.getMessage().getText().equals("Выход")) {
                    User user = userService.findByChatId(update.getMessage().getChatId()).get();
                    if(!user.isAtWork() && user.getLastUpdate() != null &&
                            (user.getLastUpdate().getDayOfMonth() == LocalDate.now().getDayOfMonth() &&
                                    user.getLastUpdate().getDayOfWeek() == LocalDate.now().getDayOfWeek())) {
                        sendMessage.setText("Вы уже вышли");
                    }
                    else {
                        userService.setIsAtWork(user.getChatID(), false);
                        LocalDateTime lastUpdate = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
                        userService.setLastUpdate(user.getChatID(), lastUpdate);
                        sendMessage.setText("Вы вышли");
                        sendMessage.setChatId(String.valueOf(message.getChatId()));
                    }
                    try {
                        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                        this.execute(sendMessage);
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
                    sendMessage.setText("Настройки изменены");
                    sendMessage.setChatId(data[0]);
                    execute(sendMessage);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
            }
    /**
     Метод обновляет и возврощает коэффицинет  опоздания и увеличивает его при необходимости
     */
    public double late(User user) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        LocalDateTime lastUpdate = user.getLastUpdate();
        LocalTime begin = user.getTimeStartWorkDay();
        double coeff = 0.0;
        if(lastUpdate != null && lastUpdate.getDayOfMonth() == now.getDayOfMonth() && lastUpdate.getDayOfWeek() == now.getDayOfWeek()) {
            coeff = user.getCoeff();
            coeff += getDifferent(now.toLocalTime(), lastUpdate.toLocalTime());
        }
        else {
            coeff = getDifferent(now.toLocalTime(), begin);
        }
            lastUpdate = LocalDateTime.now();
            userService.setLastUpdate(user.getChatID(), lastUpdate);
            userService.setCoeff(user.getChatID(), coeff);
        return coeff;
    }
    /**
        Метод возвращает разницу между двумя промежутками
     */
    public double getDifferent(LocalTime now, LocalTime begin) {
        int countOfHours = (now.getHour() - begin.getHour());
        int countOfMinutes = (now.getMinute() - begin.getMinute());
        int total = countOfHours * 60 + countOfMinutes;
        int finHour = total / 60;
        int finMin = total % 60;
        double coeff = finHour;
        if ((finMin > 0) && (finMin <= 6)) {
            coeff = coeff + 0.1;
        } else if ((finMin > 6) && (finMin <= 12)) {
            coeff = coeff + 0.2;
        } else if ((finMin > 12) && (finMin <= 18)) {
            coeff = coeff + 0.3;
        } else if ((finMin > 18) && (finMin <= 24)) {
            coeff = coeff + 0.4;
        } else if ((finMin > 24) && (finMin <= 30)) {
            coeff = coeff + 0.5;
        } else if ((finMin > 30) && (finMin <= 36)) {
            coeff = coeff + 0.6;
        } else if ((finMin > 36) && (finMin <= 42)) {
            coeff = coeff + 0.7;
        } else if ((finMin > 42) && (finMin <= 48)) {
            coeff = coeff + 0.8;
        } else if ((finMin > 48) && (finMin <= 54)) {
            coeff = coeff + 0.9;
        }
     return coeff;
    }
    }

