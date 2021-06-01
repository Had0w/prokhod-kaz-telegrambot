package com.klyuev.prokhodkaztelegrambot.bot;

import com.klyuev.prokhodkaztelegrambot.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.service.UserService;
import com.klyuev.prokhodkaztelegrambot.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UserServiceImpl.class)
public class ProkhodKAZBotTest {

    @MockBean
    UserService userService;

    @Before
     public void init() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setChatID(0);
        user1.setTimeStartWorkDay(LocalTime.of(7,29));
        user1.setTimeOfLunch(LocalTime.of(12, 1));
        user1.setTimeOfEndWorkDay(LocalTime.of(16, 31));
        user1.setAtWork(false);
        User user2 = new User();
        user2.setChatID(1);
        user2.setTimeStartWorkDay(LocalTime.of(7,59));
        user2.setTimeOfLunch(LocalTime.of(12, 1));
        user2.setTimeOfEndWorkDay(LocalTime.of(17, 1));
        user2.setAtWork(false);
        users.add(user1);
        users.add(user2);
        Mockito.when(userService.findByChatId(0)).thenReturn(users.get(0));
        Mockito.when(userService.findByChatId(1)).thenReturn(users.get(1));
    }

    @Test
    public void getDifferent() {
        LocalTime now = LocalTime.of(8, 30);
        User user = userService.findByChatId(0);
        LocalTime begin = user.getTimeStartWorkDay();
        int balance = user.getBalance();
        double coeff = new ProkhodKAZBot().getDifferent(now, begin, balance);
        double expextedCoeff = 1.1;
        Assert.assertEquals(expextedCoeff, coeff, 0.01);
    }

    @Test
    public void subtractLunch() {

    }
}