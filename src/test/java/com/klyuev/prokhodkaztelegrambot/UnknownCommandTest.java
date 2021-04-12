package com.klyuev.prokhodkaztelegrambot;

import com.klyuev.prokhodkaztelegrambot.command.Command;
import com.klyuev.prokhodkaztelegrambot.command.CommandNames;
import com.klyuev.prokhodkaztelegrambot.command.StartCommand;
import com.klyuev.prokhodkaztelegrambot.command.UnknownCommand;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Unit test for UnknownCommand")
public class UnknownCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return CommandNames.UNKNOWN_COMMAND.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return UnknownCommand.UNKNOWN_COMMAND_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}
