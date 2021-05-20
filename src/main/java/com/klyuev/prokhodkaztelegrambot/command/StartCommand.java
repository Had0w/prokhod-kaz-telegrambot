package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.bot.ProkhodKAZBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartCommand implements Command{
    private ProkhodKAZBot prokhodKAZBot;

    @Autowired
    public void setProkhodKAZBot(ProkhodKAZBot prokhodKAZBot) {
        this.prokhodKAZBot = prokhodKAZBot;
    }

    @Override
    public void execute(ProkhodKAZBot prokhodKAZBot) {

    }
}
