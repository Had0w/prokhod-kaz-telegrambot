package com.klyuev.prokhodkaztelegrambot;

import com.klyuev.prokhodkaztelegrambot.bot.ProkhodKAZBot;
import com.klyuev.prokhodkaztelegrambot.command.Command;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Abstract class for testing {@link Command}s.
 */
public abstract class AbstractCommandTest {

    protected ProkhodKAZBot prokhodKAZBot = Mockito.mock(ProkhodKAZBot.class);
    protected SendBotMessageServiceImpl sendBotMessageService = new SendBotMessageServiceImpl(prokhodKAZBot);

    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {
        //given
        Long chatId = 123456789011L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandMessage());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        //when
        getCommand().execute(update);
        //then
        Mockito.verify(prokhodKAZBot).execute(sendMessage);
    }
}
