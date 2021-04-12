package com.klyuev.prokhodkaztelegrambot;

import com.klyuev.prokhodkaztelegrambot.bot.ProkhodKAZBot;
import com.klyuev.prokhodkaztelegrambot.service.SendBotMessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit test for SendBotMessageService")
public class SendBotMessageServiceTest {
    private SendBotMessageServiceImpl sendBotMessageService;
    private ProkhodKAZBot prokhodKAZBot;

    @BeforeEach
    public void init() {
        prokhodKAZBot = Mockito.mock(ProkhodKAZBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(prokhodKAZBot);
    }
    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        //given
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        //when
        sendBotMessageService.sendMessage(chatId, message);

        //then
        Mockito.verify(prokhodKAZBot).execute(sendMessage);
    }
}
