package com.klyuev.prokhodkaztelegrambot.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "users")
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
    private LocalDate lastUpdate;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name = "coeff")
    private double coeff;
    @Column(name = "lastentry")
    private LocalDate lastEntry;
    @Column(name = "lastexit")
    private LocalDate lastExit;

    public User() {
    }

    public User(long chatID) {
        this.chatID = chatID;
        this.lastUpdate = LocalDate.now();
        this.timeStartWorkDay = LocalTime.of(8, 0);
        this.timeOfLunch = LocalTime.of(12, 0);
        this.timeOfEndWorkDay = LocalTime.of(17, 0);
        this.role = Role.USER;
        this.coeff = 0.0;
    }
}
