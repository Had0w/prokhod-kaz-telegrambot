package com.klyuev.prokhodkaztelegrambot.repository;
import com.klyuev.prokhodkaztelegrambot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
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

    @Transactional
    @Modifying
    @Query(value = "update User user set user.isAtWork = :bool where user.chatID = :chatId")
    void setIsAtWork(@Param("chatId") Long chatId, @Param("bool")Boolean bool);

    @Transactional
    @Modifying
    @Query(value = "update User user set user.lastUpdate = :lastUpdate where user.chatID = :chatId")
    void setLastUpdate(@Param("chatId") Long chatId, @Param("lastUpdate") LocalDateTime lastUpdate);

    @Transactional
    @Modifying
    @Query(value = "update User user set user.coeff = :coeff where user.chatID = :chatId")
    void setCoeff(@Param("chatId") Long chatId, @Param("coeff") double coeff);

    @Transactional
    @Modifying
    @Query(value = "update User user set user.balance = :balance where user.chatID = :chatId")
    void setBalance(@Param("chatId") Long chatId, @Param("balance") int balance);
}
