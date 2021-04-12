package com.klyuev.prokhodkaztelegrambot;

import com.klyuev.prokhodkaztelegrambot.command.Command;
import com.klyuev.prokhodkaztelegrambot.command.CommandContainer;
import com.klyuev.prokhodkaztelegrambot.command.CommandNames;
import com.klyuev.prokhodkaztelegrambot.command.UnknownCommand;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageService;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

@DisplayName("Unit test for CommandContainer")
public class CommandContainerTest {
    private CommandContainer commandContainer;
    private SendBotMessageServiceImpl sendBotMessageService;

    @BeforeEach
    public void init() {
        sendBotMessageService = Mockito.mock(SendBotMessageServiceImpl.class);
        commandContainer = new CommandContainer(sendBotMessageService);
    }

    /**
     * Container has have all known command
     */
    @Test
    public void shouldHasAllCommands() {
        Arrays.stream(CommandNames.values()).forEach(container -> {
            Command command = commandContainer.retrieveCommand(container.getCommandName());
            Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
        });
    }

    /**
     * Container has have a right reaction to unknown command
     */
    @Test
    public void shouldHasUnknownCommand() {
        //given
        String commandName = "/sdsfefaef";
        //when
        Command command = commandContainer.retrieveCommand(commandName);
        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass()
        );
    }
}
