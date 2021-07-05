package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
При команде "Вход" программа проверяет на значене параметра isAtWork и когда был последний апдейт,
если не в текущий день, то операцуия выролняется
 */
@Component
public class EnterCommand implements Command {
    private int balance;
    private final SendBotMessageServiceImpl sendBotMessageService;

    public EnterCommand(SendBotMessageServiceImpl sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update, UserServiceImpl userService) {
        String text;
        User user = userService.findByChatId(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(user.getChatID()));
        LocalDateTime now = LocalDateTime.now((ZoneId.of("Europe/Moscow")));
        if (user.getLastUpdate() != null && !user.getLastUpdate().toLocalDate().equals(now.toLocalDate())) {
            /**
             * Если последнее обновление было не сегодня, то все значения обнуляются, как у юсера так и в репозитории
             */
            user.setCoeff(0.0);
            user.setAtWork(false);
            user.setBalance(0);
            userService.setBalance(user.getChatID(), 0);
            userService.setCoeff(user.getChatID(), 0.0);
            userService.setIsAtWork(user.getChatID(), false);
        }
        if (user.getTimeOfEndWorkDay().isBefore(now.toLocalTime())) {
            text = "Ваш рабочий день уже закончился";
        } else if (user.getLastUpdate() != null && user.getLastUpdate().toLocalDate().equals(now.toLocalDate()) && user.isAtWork()) {
            text = "Вы уже на работе";
        } else {
            userService.setIsAtWork(user.getChatID(), true);
            if (user.getTimeStartWorkDay().isBefore(now.toLocalTime())) {
                double coeff = late(user, now, userService);
                text = "Вы вошли, ваше опоздание " + String.format("%.1f", coeff);
            } else {
                text = "Вы вошли и не опоздали";
            }
            userService.setLastUpdate(user.getChatID(), LocalDateTime.now());
            userService.setIsAtWork(user.getChatID(), true);
        }
        sendMessage.setText(text);
        sendBotMessageService.sendMessage(sendMessage);
    }

    /**
     * Метод обновляет и возврощает коэффицинет  опоздания и увеличивает его при необходимости
     */
    public double late(User user, LocalDateTime now, UserServiceImpl userService) {
        LocalDateTime lastUpdate = user.getLastUpdate();
        LocalTime begin = user.getTimeStartWorkDay();
        LocalTime lunch = user.getTimeOfLunch().plusMinutes(1);
        int userBalance = user.getBalance();
        double coeff = user.getCoeff();
        if (lastUpdate != null && lastUpdate.toLocalDate().equals(now.toLocalDate())) {
            coeff += subtractLunch(now.toLocalTime(), lastUpdate.toLocalTime(), lunch, getDifferent(now.toLocalTime(), lastUpdate.toLocalTime(), userBalance));
        } else {
            coeff += subtractLunch(now.toLocalTime(), begin, lunch, getDifferent(now.toLocalTime(), begin, userBalance));
        }
        userService.setBalance(user.getChatID(), balance);
        lastUpdate = LocalDateTime.now();
        userService.setLastUpdate(user.getChatID(), lastUpdate);
        userService.setCoeff(user.getChatID(), coeff);
        return coeff;
    }

    /**
     * Метод возвращает разницу между двумя промежутками
     */
    public double getDifferent(LocalTime now, LocalTime begin, int userBalance) {
        int countOfHours = (now.getHour() - begin.getHour());
        int countOfMinutes = (now.getMinute() - begin.getMinute());
        int total = countOfHours * 60 + countOfMinutes;
        if (userBalance >= total) {
            userBalance -= total;
            balance = userBalance;
            return 0.0;
        } else {
            //заменил balance на userBalance для корректонго теста
            total -= userBalance;
            balance = 0;
        }
       double coeff = getCoeff(total);
        balance = 6 - total % 6;
        if (balance == 6) {
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
        double coeff = getCoeff(total);
        /**
         * Остаток времени, которое пользователь уже использовал
         */
        return coeff;
    }

    public double getCoeff(int total) {
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

    public double subtractLunch(LocalTime now, LocalTime begin, LocalTime lunch, double coeff) {

        LocalTime endLunch = lunch.plusMinutes(58);
        /**
         * Если приход и уход был в обед, то коэффициент не увеличивается
         */
        if (now.isAfter(lunch) && now.isBefore(endLunch) && begin.isAfter(lunch) && begin.isBefore(endLunch)) {
            coeff = 0.0;
        }
        /**
         * Если уход до обеда, а приход после, то опоздание уменьшается на 1 час
         */
        else if (begin.isBefore(lunch) && now.isAfter(endLunch)) {
            coeff -= 1.0;
        }
        /**
         * Если уход до обеда, а приход в обед
         */
        else if (begin.isBefore(lunch) && now.isAfter(lunch) && now.isBefore(endLunch)) {
            coeff -= getDifferentForLunchTime(now, lunch);
        }
        /**
         * Если уход в обед, а приход после обеда
         */
        else if (begin.isAfter(lunch) && begin.isBefore(endLunch) && now.isAfter(endLunch)) {
            coeff -= getDifferentForLunchTime(endLunch, begin);
        }
        return coeff;
    }
}
