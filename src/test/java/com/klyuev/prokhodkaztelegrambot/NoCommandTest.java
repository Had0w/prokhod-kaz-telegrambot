package com.klyuev.prokhodkaztelegrambot;

import com.klyuev.prokhodkaztelegrambot.command.Command;
import com.klyuev.prokhodkaztelegrambot.command.CommandNames;
import com.klyuev.prokhodkaztelegrambot.command.NoCommand;
import com.klyuev.prokhodkaztelegrambot.command.StartCommand;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Unit test for NoCommand")
public class NoCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return CommandNames.NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NoCommand.NO_COMMAND_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}