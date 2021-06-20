package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.bot.ProkhodKAZBot;
import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
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

import java.time.LocalDateTime;
import java.time.LocalTime;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UserServiceImpl.class)
public class StartCommandTest {

    @MockBean
    Update update;

    @MockBean
    UserServiceImpl userService;

    @Before
    public void init() {
        User user1 = new User();
        user1.setTimeStartWorkDay(LocalTime.of(8, 30));
        user1.setTimeOfEndWorkDay(LocalTime.of(17, 30));
        Mockito.when(userService.findByChatId(1)).thenReturn(user1);
        Mockito.when(update.getMessage().getChatId()).thenReturn(1L);
    }

    @Test
    public void execute() {
        ProkhodKAZBot prokhodKAZBot = new ProkhodKAZBot();
        prokhodKAZBot.setUserService(userService);
        new StartCommand(new SendBotMessageServiceImpl(prokhodKAZBot)).execute(update, userService);
    }
}