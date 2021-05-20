package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.bot.ProkhodKAZBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandContainer {
    private ProkhodKAZBot prokhodKAZBot;
    private Map<String, Command> COMMANDS;

    @Autowired
    public void setProkhodKAZBot(ProkhodKAZBot prokhodKAZBot) {
        this.prokhodKAZBot = prokhodKAZBot;
    }

    public CommandContainer() {
       COMMANDS = new HashMap<>();
        COMMANDS.put("/start", new StartCommand());
    }

    public void usingCommand(String command, ProkhodKAZBot prokhodKAZBot) {
        Command command1 = COMMANDS.get(command);
        command1.execute(prokhodKAZBot);
    }
}
