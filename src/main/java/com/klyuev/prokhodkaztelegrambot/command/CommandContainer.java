package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandContainer {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandContainer(SendBotMessageServiceImpl sendBotMessageService) {
        commands.put(CommandName.START.getCommandName(), new StartCommand(sendBotMessageService));
        commands.put(CommandName.ENTER.getCommandName(), new EnterCommand(sendBotMessageService));
        commands.put(CommandName.EXIT.getCommandName(), new ExitCommand(sendBotMessageService));
        commands.put(CommandName.SETTING.getCommandName(), new SettingCommand(sendBotMessageService));
        commands.put(CommandName.INFO.getCommandName(), new InfoCommand(sendBotMessageService));
        commands.put(CommandName.UNKNOWN.getCommandName(), new UnknownCommand(sendBotMessageService));
        commands.put(CommandName.STATISTIC.getCommandName(), new StatisticCommand(sendBotMessageService));
        commands.put(CommandName.BALANCE.getCommandName(), new BalanceCommand(sendBotMessageService));
    }

    public Command retrieveCommand(String commandName) {
        if (commands.containsKey(commandName)) {
            return commands.get(commandName);
        } else return commands.get("Неизветсная команда");
    }
}
