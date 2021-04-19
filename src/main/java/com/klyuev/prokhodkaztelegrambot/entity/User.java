package com.klyuev.prokhodkaztelegrambot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@Component
@Table(name = "users")
public class User {
    @Id
    @Column(name = "chatId")
    private long chatID;
    @Column(name = "timeOfStartWorkDay")
    private LocalTime timeStartWorkDay;
    @Column(name = "timeOfLunch")
    private LocalTime timeOfLunch;
    @Column(name = "timeOfEndWorkDay")
    private LocalTime timeOfEndWorkDay;
    @Column(name = "isAtWork")
    private boolean isAtWork;
    @Column(name = "lastUpdate")
    private LocalDate lastUpdate;

    public User(long chatID) {
        this.chatID = chatID;
        this.lastUpdate = LocalDate.now();
    }
}
