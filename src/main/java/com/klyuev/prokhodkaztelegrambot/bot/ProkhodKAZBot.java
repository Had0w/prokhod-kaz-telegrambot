package com.klyuev.prokhodkaztelegrambot.bot;

import com.klyuev.prokhodkaztelegrambot.command.CommandContainer;
import com.klyuev.prokhodkaztelegrambot.command.CommandNames;
import com.klyuev.prokhodkaztelegrambot.command.UnknownCommand;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ProkhodKAZBot extends TelegramLongPollingBot {
    private final String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;
    private SendBotMessageServiceImpl sendBotMessageService;
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;
    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public ProkhodKAZBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if(message.startsWith(COMMAND_PREFIX)) {
                String command = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(command).execute(update);
            }
            else {
                commandContainer.retrieveCommand("no").execute(update);
            }
        }
    }
}
