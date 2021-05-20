package com.klyuev.prokhodkaztelegrambot.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "users")
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

    public long getChatID() {
        return chatID;
    }

    public void setChatID(long chatID) {
        this.chatID = chatID;
    }

    public LocalTime getTimeStartWorkDay() {
        return timeStartWorkDay;
    }

    public void setTimeStartWorkDay(LocalTime timeStartWorkDay) {
        this.timeStartWorkDay = timeStartWorkDay;
    }

    public LocalTime getTimeOfLunch() {
        return timeOfLunch;
    }

    public void setTimeOfLunch(LocalTime timeOfLunch) {
        this.timeOfLunch = timeOfLunch;
    }

    public LocalTime getTimeOfEndWorkDay() {
        return timeOfEndWorkDay;
    }

    public void setTimeOfEndWorkDay(LocalTime timeOfEndWorkDay) {
        this.timeOfEndWorkDay = timeOfEndWorkDay;
    }

    public boolean isAtWork() {
        return isAtWork;
    }

    public void setAtWork(boolean atWork) {
        isAtWork = atWork;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Role getRole() {
        return role;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
