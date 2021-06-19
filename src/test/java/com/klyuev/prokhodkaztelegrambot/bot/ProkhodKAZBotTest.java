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
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UserServiceImpl.class)
public class ProkhodKAZBotTest {
//    static List<User> users = new ArrayList<>();
//    static ProkhodKAZBot prokhodKAZBot = new ProkhodKAZBot();
//
//    @MockBean
//    UserServiceImpl userService;
//
//    @MockBean
//    Update update;
//
//    @Before
//     public void init() {
//        User user1 = new User();
//        user1.setChatID(0);
//        user1.setTimeStartWorkDay(LocalTime.of(7,29));
//        user1.setTimeOfLunch(LocalTime.of(12, 1));
//        user1.setTimeOfEndWorkDay(LocalTime.of(16, 31));
//        user1.setAtWork(false);
//        User user2 = new User();
//        user2.setChatID(1);
//        user2.setTimeStartWorkDay(LocalTime.now().minusHours(2).minusMinutes(32));
//        user2.setTimeOfLunch(LocalTime.now().plusHours(2));
//        user2.setTimeOfEndWorkDay(LocalTime.now().minusHours(4));
//        user2.setCoeff(2.2);
//        user2.setLastUpdate(LocalDateTime.now().minusDays(1).minusHours(1));
//        user2.setAtWork(true);
//        users.add(user1);
//        users.add(user2);
//        Mockito.when(userService.findByChatId(0)).thenReturn(users.get(0));
//        Mockito.when(userService.findByChatId(1)).thenReturn(users.get(1));
//        prokhodKAZBot.setUserService(userService);
//    }
//
//    @Test
//    public void getDifferent() {
//        LocalTime now = LocalTime.of(8, 30);
//        User user = userService.findByChatId(0);
//        LocalTime begin = user.getTimeStartWorkDay();
//        int balance = user.getBalance();
//        double coeff = new ProkhodKAZBot().getDifferent(now, begin, balance);
//        double expextedCoeff = 1.1;
//        Assert.assertEquals(expextedCoeff, coeff, 0.01);
//    }
//
//    @Test
//    public void late() {
//        User user = new User();
//        user.setChatID(3);
//        user.setCoeff(0.0);
//        user.setAtWork(true);
//        user.setBalance(0);
//        user.setTimeStartWorkDay(LocalTime.now().minusHours(2));
//        user.setTimeOfLunch(LocalTime.now().plusHours(2));
//        user.setTimeOfEndWorkDay(LocalTime.now().plusHours(7));
//        user.setLastUpdate(LocalDateTime.now().minusDays(1));
//        double actual = prokhodKAZBot.late(user, LocalDateTime.now());
//        double expected = 2.0;
//        Assert.assertEquals(expected, actual, 0.01);
//    }
//
////    @Test
////    public void onUpdateReceived() {
////        Mockito.when(update.getMessage().getChatId()).thenReturn(1L);
////        Mockito.when(update.getMessage().getText()).thenReturn("Вход");
////        new ProkhodKAZBot().onUpdateReceived(update);
////
////    }
//    @Test
//    public void subtractLunch() {
//
//    }
}