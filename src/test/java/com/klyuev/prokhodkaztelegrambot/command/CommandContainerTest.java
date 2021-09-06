package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandContainerTest {

    @MockBean
    SendBotMessageServiceImpl sendBotMessageService;

    @Test
    public void retrieveCommandEnter() {
       Command command =  new CommandContainer(sendBotMessageService).retrieveCommand("Вход");
       boolean actual = command instanceof EnterCommand;
       Assert.assertTrue(actual);
    }

    @Test
    public void retrieveCommandExit() {
        Command command = new CommandContainer(sendBotMessageService).retrieveCommand("Выход");
        boolean actual = command instanceof ExitCommand;
        Assert.assertTrue(actual);
    }

    @Test
    public void retrieveCommandUnknown() {
        Command command = new CommandContainer(sendBotMessageService).retrieveCommand("sdsdsdsd");
        boolean actual = command instanceof UnknownCommand;
        Assert.assertTrue(actual);
    }
}