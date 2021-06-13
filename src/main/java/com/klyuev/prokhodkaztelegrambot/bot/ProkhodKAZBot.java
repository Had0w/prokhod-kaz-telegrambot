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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProkhodKAZBot extends TelegramLongPollingBot {
    private int balance;
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
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();

        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                /**
                 * Стартовая команда, проверяет есть ли пользователь по данному chatId в базе, если нет, то создает нового
                 */
                if (update.getMessage().getText().equals("/start")) {
                    if (!userService.containsUser(message.getChatId())) {
                        sendMessage.setText("Добро пожаловать! По умолчанию начало рабочего дня в 8:00, конец в 17:00");
                        User user = new User(message.getChatId());
                        userService.addNewUser(user);
                    } else {
                        User user = userService.findByChatId(message.getChatId());
                        LocalTime localTimeStart = user.getTimeStartWorkDay().plusMinutes(1);
                        String lineStart = localTimeStart.format(DateTimeFormatter.ofPattern("HH:mm"));
                        LocalTime localTimeEnd = user.getTimeOfEndWorkDay().minusMinutes(1);
                        String lineEnd = localTimeEnd.format(DateTimeFormatter.ofPattern("HH:mm"));
                        sendMessage.setText("С возвращением! Начало вашего рабочего дня в " + lineStart + " конец в " + lineEnd);
                    }
                    sendMessage.setChatId(message.getChatId().toString());
                    try {
                        this.execute(sendReplyKeyboardMarkupMessage(Long.parseLong(sendMessage.getChatId()), sendMessage.getText()));

                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                /**
                 * Запускает настройку параметров пользователя
                 */
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
                    User user = userService.findByChatId(update.getMessage().getChatId());
                    LocalDateTime now = LocalDateTime.now((ZoneId.of("Europe/Moscow")));
                    if(user.getLastUpdate() != null && (user.getLastUpdate().getDayOfMonth() != now.getDayOfMonth() ||
                            user.getLastUpdate().getDayOfWeek() != now.getDayOfWeek())) {
                            userService.setCoeff(user.getChatID(), 0.0);
                            user.setCoeff(0.0);
                            userService.setIsAtWork(user.getChatID(), false);
                    }
                    if(user.getTimeOfEndWorkDay().isBefore(now.toLocalTime())) {
                        sendMessage.setText("Ваш рабочий день уже закончился");
                    }
                    else if(user.isAtWork()) {
                        sendMessage.setText("Вы уже на работе");
                    }
                    else {
                        userService.setIsAtWork(user.getChatID(), true);
                        if(user.getTimeStartWorkDay().isBefore(now.toLocalTime())) {
                            double coeff = late(user, now);
                            sendMessage.setText("Вы вошли, ваше опоздание " + String.format("%.1f", coeff));
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
                    User user = userService.findByChatId(update.getMessage().getChatId());
                    if(!user.isAtWork() && user.getLastUpdate() != null &&
                            (user.getLastUpdate().getDayOfMonth() == LocalDate.now().getDayOfMonth() ||
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
                else if(update.getMessage().getText().equals("Справка")) {
                    String info = "- Данный бот является информационным и не связан с проходной системой предприятия\n" +
                            "-При нажатии кнопки \"Вход\"  производится расчет коэфициена опоздания, нажмите данную кнопку при использовании " +
                            "контрольного устройства на ВАШЕМ рабочем месте, не на проходной!\n " +
                            "-При уходе с рабочего места в течении дня используйте данный бот за пять минут ДО и ПОСЛЕ прохода через основную проходную\n" +
                            "-Вы можете настроить начало и конец вашего рабочего дня";
                    sendMessage.setText(info);
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
    public double late(User user, LocalDateTime now) {
        LocalDateTime lastUpdate = user.getLastUpdate();
        LocalTime begin = user.getTimeStartWorkDay();
        LocalTime lunch = user.getTimeOfLunch();
        int userBalance = user.getBalance();
        double coeff = user.getCoeff();
        if(lastUpdate != null && lastUpdate.getDayOfMonth() == now.getDayOfMonth() && lastUpdate.getDayOfWeek() == now.getDayOfWeek()) {
            coeff += subtractLunch(now.toLocalTime(), lastUpdate.toLocalTime(), lunch, getDifferent(now.toLocalTime(), lastUpdate.toLocalTime(), userBalance));
        }
        else {
            coeff += subtractLunch(now.toLocalTime(), begin, lunch, getDifferent(now.toLocalTime(), begin, userBalance));
        }
            userService.setBalance(user.getChatID(), balance);
            lastUpdate = LocalDateTime.now();
            userService.setLastUpdate(user.getChatID(), lastUpdate);
            userService.setCoeff(user.getChatID(), coeff);
        return coeff;
    }
    /**
        Метод возвращает разницу между двумя промежутками
     */
    public double getDifferent(LocalTime now, LocalTime begin, int userBalance) {
        int countOfHours = (now.getHour() - begin.getHour());
        int countOfMinutes = (now.getMinute() - begin.getMinute());
        int total = countOfHours * 60 + countOfMinutes;
        if(userBalance >= total) {
            userBalance -= total;
            balance = userBalance;
            return 0.0;
        }
        else {
            //заменил balance на userBalance для корректонго теста
            total -= userBalance;
            balance = 0;
        }
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
        balance = 6 - total%6;
        if(balance == 6) {
            balance = 0;
        }
        /**
         * Остаток времени, которое пользователь уже использовал
         */
     return coeff;
    }

    public double getDifferentForLunchTime(LocalTime now, LocalTime begin) {
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
        /**
         * Остаток времени, которое пользователь уже использовал
         */
        return coeff;
    }

    public double subtractLunch(LocalTime now, LocalTime begin, LocalTime lunch, double coeff) {
        LocalTime endLunch = lunch.plusMinutes(58);
        /**
         * Если приход и уход был в обед, то коэффициент не увеличивается
         */
        if(now.isAfter(lunch) && now.isBefore(endLunch) && begin.isAfter(lunch) && begin.isBefore(endLunch)) {
            coeff = 0.0;
        }
        /**
         * Если уход до обеда, а приход после, то опоздание уменьшается на 1 час
         */
        else if(begin.isBefore(lunch) && now.isAfter(endLunch)) {
            coeff -= 1.0;
        }
        /**
         * Если уход до обеда, а приход в обед
         */
        else if(begin.isBefore(lunch) && now.isAfter(lunch) && now.isBefore(endLunch)) {
            coeff -= getDifferentForLunchTime(now, lunch);
        }
        /**
         * Если уход в обед, а приход после обеда
         */
        else if(begin.isAfter(lunch) && begin.isBefore(endLunch) && now.isAfter(endLunch)) {
            coeff -= getDifferentForLunchTime(endLunch, begin);
        }
        return coeff;
    }
    }

