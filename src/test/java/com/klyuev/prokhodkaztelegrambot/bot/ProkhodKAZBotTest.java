package com.klyuev.prokhodkaztelegrambot.bot;

import com.klyuev.prokhodkaztelegrambot.command.CommandContainer;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProkhodKAZBotTest {
    ProkhodKAZBot underTest = new ProkhodKAZBot();

    @Mock
    private Message message;

    @Mock
    private Update update;

    @Mock
    private CommandContainer commandContainer;

    @Mock
    private UserServiceImpl userService;

    @BeforeEach
    public void init() {
        underTest.setUserService(userService);

    }

    @Test
    public void onUpdateReceivedHasMessage() {
        Mockito.when(message.getText()).thenReturn("Вход");
        Mockito.when(update.hasMessage()).thenReturn(true);
        Mockito.when(update.getMessage()).thenReturn(message);
        underTest.onUpdateReceived(update);
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(commandContainer).retrieveCommand(stringArgumentCaptor.capture());
        String expectedText = "Вход";
        Assert.assertEquals(expectedText, stringArgumentCaptor.getValue());
    }
}