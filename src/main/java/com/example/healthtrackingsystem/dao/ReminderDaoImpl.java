package com.example.healthtrackingsystem.dao;
import com.example.healthtrackingsystem.Interfaces.ReminderDao;
import com.example.healthtrackingsystem.Models.Reminder;
import com.example.healthtrackingsystem.Models.ReminderBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReminderDaoImpl implements ReminderDao {

    @Override
    public List<Reminder> findAllByUserId(int userId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<Reminder> reminders = new ArrayList<>();

        String query = "SELECT * FROM reminder WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reminder reminder = new ReminderBuilder()
                        .reminderId(resultSet.getInt("reminder_id"))
                        .userId(resultSet.getInt("user_id"))
                        .reminderMessage(resultSet.getString("reminder_message"))
                        .reminderPeriod(resultSet.getInt("reminder_period"))
                        .build();

                reminders.add(reminder);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return reminders;
    }

    @Override
    public Reminder findById(int reminderId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM reminder WHERE reminder_id = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, reminderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new ReminderBuilder()
                        .reminderId(resultSet.getInt("reminder_id"))
                        .userId(resultSet.getInt("user_id"))
                        .reminderMessage(resultSet.getString("reminder_message"))
                        .reminderPeriod(resultSet.getInt("reminder_period"))
                        .build();
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void save(Reminder reminder) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "INSERT INTO reminder (user_id, reminder_message, reminder_period) VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, reminder.getUserId());
            preparedStatement.setString(2, reminder.getReminderMessage());
            preparedStatement.setInt(3, reminder.getReminderPeriod());

            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public void updateReminder(Reminder reminder) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "UPDATE reminder SET user_id=?, reminder_message=?, reminder_period=? WHERE reminder_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, reminder.getUserId());
            preparedStatement.setString(2, reminder.getReminderMessage());
            preparedStatement.setInt(3, reminder.getReminderPeriod());
            preparedStatement.setInt(4, reminder.getReminderId());

            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(int reminderId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "DELETE FROM reminder WHERE reminder_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, reminderId);

            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
