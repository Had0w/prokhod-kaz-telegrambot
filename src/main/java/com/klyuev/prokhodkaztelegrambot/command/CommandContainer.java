package com.klyuev.prokhodkaztelegrambot.command;

import com.google.common.collect.ImmutableMap;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageServiceImpl sendBotMessageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandNames.START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(CommandNames.RESET.getCommandName(), new ResetCommand(sendBotMessageService))
                .put(CommandNames.NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
