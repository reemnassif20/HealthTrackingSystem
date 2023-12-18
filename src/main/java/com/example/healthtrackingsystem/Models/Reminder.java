package com.example.healthtrackingsystem.Models;

public class Reminder {
    private int reminderId;
    private int userId;
    private String reminderMessage;
    private int reminderPeriod;

    public Reminder() {
    }

    public Reminder(int reminderId, int userId, String reminderMessage, int reminderPeriod) {
        this.reminderId = reminderId;
        this.userId = userId;
        this.reminderMessage = reminderMessage;
        this.reminderPeriod = reminderPeriod;
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReminderMessage() {
        return reminderMessage;
    }

    public void setReminderMessage(String reminderMessage) {
        this.reminderMessage = reminderMessage;
    }

    public int getReminderPeriod() {
        return reminderPeriod;
    }

    public void setReminderPeriod(int reminderPeriod) {
        this.reminderPeriod = reminderPeriod;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "reminderId=" + reminderId +
                ", userId=" + userId +
                ", reminderMessage='" + reminderMessage + '\'' +
                ", reminderPeriod=" + reminderPeriod +
                '}';
    }

    public static ReminderBuilder builder() {
        return new ReminderBuilder();
    }
}
