package com.klyuev.prokhodkaztelegrambot.repository;
import com.klyuev.prokhodkaztelegrambot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query(value = "update User user set user.timeStartWorkDay = :localTime where user.chatID = :chatId")
    void updateStartOfWorkDay(@Param("chatId") Long chatId, @Param("localTime") LocalTime localTime);

    @Transactional
    @Modifying
    @Query(value = "update User user set user.timeOfEndWorkDay = :localTime where user.chatID = :chatId")
    void updateEndOfWorkDay(@Param("chatId") Long chatId, @Param("localTime") LocalTime localTime);
}
