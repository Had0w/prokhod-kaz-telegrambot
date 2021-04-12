package com.klyuev.prokhodkaztelegrambot;

import com.klyuev.prokhodkaztelegrambot.command.Command;
import com.klyuev.prokhodkaztelegrambot.command.CommandNames;
import com.klyuev.prokhodkaztelegrambot.command.ResetCommand;
import com.klyuev.prokhodkaztelegrambot.command.StartCommand;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Unit test for ResetCommand")
public class ResetCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return CommandNames.RESET.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return ResetCommand.RESET_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new ResetCommand(sendBotMessageService);
    }
}
