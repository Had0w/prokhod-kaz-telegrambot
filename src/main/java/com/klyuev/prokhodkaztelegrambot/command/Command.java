package com.klyuev.prokhodkaztelegrambot.command;

import com.klyuev.prokhodkaztelegrambot.bot.ProkhodKAZBot;

public interface Command {
    void execute(ProkhodKAZBot prokhodKAZBot);
}
