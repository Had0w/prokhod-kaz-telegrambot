package com.klyuev.prokhodkaztelegrambot.bot;

import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.meta.api.objects.Update;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UserServiceImpl.class)
public class ProkhodKAZBotTest {

    @MockBean
    public Update update;

    @Before
    public void init() {
        Mockito.when(update.hasMessage()).thenReturn(true);
        Mockito.when(update.getMessage().getText()).thenReturn("Вход");
    }

    @Test
    public void onUpdateReceived() {

    }
}