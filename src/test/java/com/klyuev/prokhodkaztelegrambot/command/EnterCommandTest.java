package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnterCommandTest {

    @Mock
    UserServiceImpl userService;
    @Mock
    SendBotMessageServiceImpl sendBotMessageService;
    EnterCommand underTest;

    @BeforeEach
    public void init() {
        underTest = new EnterCommand(sendBotMessageService);
    }

    @Test
    void late() {
        //given
        User user = new User();
        user.setTimeStartWorkDay(LocalTime.of(7, 29));
        user.setTimeOfEndWorkDay(LocalTime.of(16, 31));
        user.setCoeff(0.0);
        user.setAtWork(false);
        user.setTimeOfLunch(LocalTime.of(12, 1));
        user.setBalance(0);
        LocalDateTime lastUpdate = LocalDateTime.now();
        lastUpdate = lastUpdate.minusDays(1);
        user.setLastUpdate(lastUpdate);
        LocalDateTime now = LocalDateTime.now();
        now = now.withHour(9).withMinute(29);
        //when
        double coeff = underTest.late(user, now, userService);
        //then
        verify(userService).setBalance(anyLong(),anyInt());
        verify(userService).setCoeff(user.getChatID(), coeff);
        verify(userService).setLastUpdate(anyLong(), anyObject());
    }

    @Test
    void lateYesterdayUpdate() {
        //given
        User user = new User();
        user.setTimeStartWorkDay(LocalTime.of(7, 29));
        user.setTimeOfEndWorkDay(LocalTime.of(16, 31));
        user.setCoeff(0.0);
        user.setAtWork(false);
        user.setTimeOfLunch(LocalTime.of(12, 1));
        user.setBalance(0);
        LocalDateTime lastUpdate = LocalDateTime.now();
        lastUpdate = lastUpdate.minusDays(1);
        user.setLastUpdate(lastUpdate);
        LocalDateTime now = LocalDateTime.now();
        now = now.withHour(9).withMinute(29);
        //when
        double actual = underTest.late(user, now, userService);
        //then
        double expected = 2.0;
        assertEquals(expected, actual);
    }

    /**
     * Если уйти в обед и вернуться
     */
    @Test
    void lateWhenLeavingAt1201() {
        //given
        User user = new User();
        user.setTimeStartWorkDay(LocalTime.of(7, 29));
        user.setTimeOfEndWorkDay(LocalTime.of(16, 31));
        user.setCoeff(0.0);
        user.setAtWork(false);
        user.setTimeOfLunch(LocalTime.of(12, 1));
        LocalDateTime lastUpdate = LocalDateTime.now();
        lastUpdate = lastUpdate.withHour(12).withMinute(1);
        user.setLastUpdate(lastUpdate);
        LocalDateTime now = LocalDateTime.now();
        now = now.withHour(15).withMinute(22);
        //when
        double actual = underTest.late(user, now, userService);
        //then
        double expected = 2.4;
        assertEquals(expected, actual);
    }
    @Test
    void lateWhenLeavingAt1230() {
        User user = new User();
        user.setTimeStartWorkDay(LocalTime.of(7, 29));
        user.setTimeOfEndWorkDay(LocalTime.of(16, 31));
        user.setCoeff(0.0);
        user.setAtWork(false);
        user.setTimeOfLunch(LocalTime.of(12, 1));
        LocalDateTime lastUpdate = LocalDateTime.now();
        lastUpdate = lastUpdate.withHour(12).withMinute(30);
        user.setLastUpdate(lastUpdate);
        LocalDateTime now = LocalDateTime.now();
        now = now.withHour(15).withMinute(0);
        //when
        double actual = underTest.late(user, now, userService);
        //then
        double expected = 2.0;
        assertEquals(expected, actual);
    }
    @Test
    void lateWhenLeavingAt1130() {
        User user = new User();
        user.setTimeStartWorkDay(LocalTime.of(7, 29));
        user.setTimeOfEndWorkDay(LocalTime.of(16, 31));
        user.setCoeff(0.0);
        user.setAtWork(false);
        user.setTimeOfLunch(LocalTime.of(12, 1));
        LocalDateTime lastUpdate = LocalDateTime.now();
        lastUpdate = lastUpdate.withHour(11).withMinute(30);
        user.setLastUpdate(lastUpdate);
        LocalDateTime now = LocalDateTime.now();
        now = now.withHour(15).withMinute(0);
        //when
        double actual = underTest.late(user, now, userService);
        //then
        double expected = 2.5;
        assertEquals(expected, actual);
    }
}