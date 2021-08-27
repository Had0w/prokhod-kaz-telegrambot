package com.klyuev.prokhodkaztelegrambot.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "users")
@DynamicUpdate
@Data
public class User {
    @Id
    @Column(name = "chatid")
    private long chatID;
    @Column(name = "timeofstartworkday")
    private LocalTime timeStartWorkDay;
    @Column(name = "timeoflunch")
    private LocalTime timeOfLunch;
    @Column(name = "timeofendworkday")
    private LocalTime timeOfEndWorkDay;
    @Column(name = "isatwork")
    private boolean isAtWork;
    @Column(name = "lastupdate")
    private LocalDateTime lastUpdate;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name = "coeff")
    private double coeff;
    @Column(name = "balance")
    private int balance;

    public User() {
    }

    public User(long chatID) {
        this.chatID = chatID;
        this.timeStartWorkDay = LocalTime.of(7, 59);
        this.timeOfLunch = LocalTime.of(12, 1);
        this.timeOfEndWorkDay = LocalTime.of(17, 1);
        this.role = Role.USER;
        this.coeff = 0.0;
    }
}
