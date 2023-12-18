package com.example.healthtrackingsystem.Models;

import java.util.Objects;

public class ReminderBuilder {
    private int reminderId;
    private int userId;
    private String reminderMessage;
    private int reminderPeriod;

    public ReminderBuilder reminderId(int reminderId) {
        this.reminderId = reminderId;
        return this;
    }

    public ReminderBuilder userId(int userId) {
        this.userId = userId;
        return this;
    }

    public ReminderBuilder reminderMessage(String reminderMessage) {
        this.reminderMessage = reminderMessage;
        return this;
    }

    public ReminderBuilder reminderPeriod(int reminderPeriod) {
        this.reminderPeriod = reminderPeriod;
        return this;
    }

    public Reminder build() {
        return new Reminder(reminderId, userId, reminderMessage, reminderPeriod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReminderBuilder that = (ReminderBuilder) o;
        return reminderId == that.reminderId &&
                userId == that.userId &&
                reminderPeriod == that.reminderPeriod &&
                Objects.equals(reminderMessage, that.reminderMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reminderId, userId, reminderMessage, reminderPeriod);
    }

    @Override
    public String toString() {
        return "ReminderBuilder{" +
                "reminderId=" + reminderId +
                ", userId=" + userId +
                ", reminderMessage='" + reminderMessage + '\'' +
                ", reminderPeriod=" + reminderPeriod +
                '}';
    }
}
