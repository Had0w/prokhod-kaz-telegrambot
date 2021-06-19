package com.klyuev.prokhodkaztelegrambot.command;

public enum CommandName {
    START("/start"),
    ENTER("Вход"),
    EXIT("Выход"),
    SETTING("Настройка"),
    INFO("Справка"),
    UNKNOWN("Неизветсная команда"),
    STATISTIC("/stat");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
