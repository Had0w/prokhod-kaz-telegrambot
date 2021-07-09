package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StartCommandTest {

    @Mock
    private SendBotMessageServiceImpl sendBotMessageService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private Update update;
    @Mock
    private Message message;

    StartCommand underTest;
    @BeforeEach
    public void setUp() {
        underTest = new StartCommand(sendBotMessageService);
        when(userService.containsUser(1L)).thenReturn(false);
        when(userService.containsUser(2L)).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        User user = new User(2L);
        user.setTimeStartWorkDay(LocalTime.of(7, 29));
        user.setTimeOfEndWorkDay(LocalTime.of(16, 31));
        when(userService.findByChatId(2L)).thenReturn(user);

    }

    @Test
    public void executeIfUserDoesnNotExist() {
        //given
        when(message.getChatId()).thenReturn(1L);
        User user = new User(1L);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        //when
        underTest.execute(update, userService);
        //then
        verify(userService).addNewUser(userArgumentCaptor.capture());//проверяем, добавлен ли новый пользователь
        Assert.assertEquals(user, userArgumentCaptor.getValue());
        String expectedText = "Добро пожаловать! По умолчанию начало рабочего дня в 8:00, конец в 17:00";
        String expectedId = "1";
        ArgumentCaptor<SendMessage> sendMessageArgumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        verify(sendBotMessageService).sendMessage(sendMessageArgumentCaptor.capture()); //проверяем, верные ли данные отправлены
        String actualText = sendMessageArgumentCaptor.getValue().getText();
        String actualId = sendMessageArgumentCaptor.getValue().getChatId();
        Assert.assertEquals(expectedText, actualText);
        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void executeUserExists() {
        //given
        when(message.getChatId()).thenReturn(2L);
        //when
        underTest.execute(update, userService);
        //then
        ArgumentCaptor<SendMessage> sendMessageArgumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        String expectedText = "С возвращением! Начало вашего рабочего дня в 07:30 конец в 16:30";
        String expectedId = "2";
        verify(sendBotMessageService).sendMessage(sendMessageArgumentCaptor.capture());
        String actualText = sendMessageArgumentCaptor.getValue().getText();
        String actualId = sendMessageArgumentCaptor.getValue().getChatId();
        Assert.assertEquals(expectedText, actualText);
        Assert.assertEquals(expectedId, actualId);
    }
}