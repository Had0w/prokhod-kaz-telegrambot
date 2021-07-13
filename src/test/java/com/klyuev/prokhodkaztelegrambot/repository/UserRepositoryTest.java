package com.klyuev.prokhodkaztelegrambot.repository;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.klyuev.prokhodkaztelegrambot.prototype.UserPrototype.createUserPrototype;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        User user = createUserPrototype(1L);
        user.setLastUpdate(LocalDateTime.now());
        userRepository.save(user);
        User user2 = createUserPrototype(2L);
        userRepository.save(user2);
    }

    @Test
    public void findByChatIdTest() {
        //when
        User foundUser = userRepository.getOne(1L);
        //then
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(createUserPrototype(1L).getChatID(), foundUser.getChatID());
    }

    @Test
    public void getCountTest() {
        //when
        int actualCount = userRepository.countAll();
        //then
        Assert.assertEquals(2, actualCount);
    }

    @Test
    public void selectLastUpdateTest() {
        //when
        LocalDateTime localDateTime = userRepository.selectLastUpdate();
        //then
        Assert.assertEquals(LocalDate.now(), localDateTime.toLocalDate());
    }

    @Test
    public void updateStartOfWorkDayTest() {
        //given
        User user = new User(2L);
        userRepository.save(user);
        //when
        userRepository.updateStartOfWorkDay(2L, LocalTime.of(8, 0));
        //then
        User foundUser = userRepository.getOne(2L);
        Assert.assertEquals(LocalTime.of(7, 59), foundUser.getTimeStartWorkDay());
    }


    @Test
    public void updateEndOfWorkDayTest() {
        //when
        userRepository.updateEndOfWorkDay(2L, LocalTime.of(16, 30));
        //then
        User user = userRepository.getOne(2L);
        Assert.assertEquals(LocalTime.of(16, 31), user.getTimeOfEndWorkDay());
    }
}