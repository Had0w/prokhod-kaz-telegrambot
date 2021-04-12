package com.klyuev.prokhodkaztelegrambot.command;

/**
 * Commands for telegram bot
 */
public enum CommandNames {
    START("/start"), RESET("/reset"), NO("no"), UNKNOWN_COMMAND("/unknown_command"), SETTING("/setting"), IN("/in"), OUT("/out");

    private final String commandName;

    CommandNames(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
