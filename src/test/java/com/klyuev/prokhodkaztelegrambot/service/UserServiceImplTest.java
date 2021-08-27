package com.klyuev.prokhodkaztelegrambot.service;

import com.klyuev.prokhodkaztelegrambot.entity.User;
import com.klyuev.prokhodkaztelegrambot.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository repository;

    private UserServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl();
        underTest.setUserRepository(repository);
    }

    @Test
    void countAll() {
        //when
        underTest.countAll();
        //then
        verify(repository).countAll();
    }

    @Test
    void selectLastUpdate() {
        //when
        underTest.selectLastUpdate();
        //then
        verify(repository).selectLastUpdate();
    }

    @Test
    void addNewUser() {
        //given
        User user = new User();
        //when
        underTest.saveUser(user);
        //then
        verify(repository).save(user);
    }

    @Test
    void findByChatIdNotFound() {
        //when
        assertThrows(NoSuchElementException.class, () -> underTest.findByChatId(1L), "No value present");
    }

    @Test
    void containsUser() {
        //when
        underTest.containsUser(1L);
        //then
        verify(repository).existsById(1L);
    }

    @Test
    void updateStartOfWorkDay() {
        //given
        LocalTime localTime = LocalTime.now();
        //when
        underTest.updateStartOfWorkDay(1L, localTime);
        //then
        verify(repository).updateStartOfWorkDay(1L, localTime);
    }

    @Test
    void updateEndOfWorkDay() {
        //given
        LocalTime localTime = LocalTime.now();
        //when
        underTest.updateEndOfWorkDay(1L, localTime);
        //then
        verify(repository).updateEndOfWorkDay(1L, localTime);
    }

    @Test
    void setIsAtWork() {
        //when
        underTest.setIsAtWork(1L, true);
        //then
        verify(repository).setIsAtWork(1L, true);
    }

    @Test
    void setLastUpdate() {
        //given
        LocalDateTime localDateTime = LocalDateTime.now();
        //when
        underTest.setLastUpdate(1L, localDateTime);
        //then
        verify(repository).setLastUpdate(1L,  localDateTime);
    }

    @Test
    void setCoeff() {
        //when
        underTest.setCoeff(1L, 3.0);
        //then
        verify(repository).setCoeff(1L, 3.0);
    }

    @Test
    void setBalance() {
        //when
        underTest.setBalance(1L, 4);
        //then
        verify(repository).setBalance(1L, 4);
    }
}