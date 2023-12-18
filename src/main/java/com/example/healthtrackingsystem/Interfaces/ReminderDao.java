package com.example.healthtrackingsystem.Interfaces;

import com.example.healthtrackingsystem.Models.Reminder;

import java.util.List;

public interface ReminderDao {
    List<Reminder> findAllByUserId(int userId);

    Reminder findById(int reminderId);

    void save(Reminder reminder);

    void updateReminder(Reminder reminder);

    void deleteById(int reminderId);
}
