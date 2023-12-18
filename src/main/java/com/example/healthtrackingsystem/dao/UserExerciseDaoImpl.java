package com.example.healthtrackingsystem.dao;

import com.example.healthtrackingsystem.Interfaces.UserExerciseDao;
import com.example.healthtrackingsystem.Models.UserExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UserExerciseDaoImpl implements UserExerciseDao {

    @Override
    public List<UserExercise> findAll() {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<UserExercise> userExercises = new LinkedList<>();

        String query = "SELECT * FROM UserExercises;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserExercise userExercise = UserExercise.builder()
                        .userId(resultSet.getInt("user_id"))
                        .exerciseId(resultSet.getInt("exercise_id"))
                        .exerciseDate(resultSet.getDate("exercise_date"))
                        .duration(resultSet.getInt("duration"))
                        .build();

                userExercises.add(userExercise);
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

        return userExercises;
    }

    @Override
    public UserExercise findByUserIdAndExerciseIdAndExerciseDate(int userId, int exerciseId, Date exerciseDate) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM UserExercises WHERE user_id=? AND exercise_id=? AND exercise_date=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, exerciseId);
            preparedStatement.setDate(3, new java.sql.Date(exerciseDate.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return UserExercise.builder()
                        .userId(resultSet.getInt("user_id"))
                        .exerciseId(resultSet.getInt("exercise_id"))
                        .exerciseDate(resultSet.getDate("exercise_date"))
                        .duration(resultSet.getInt("duration"))
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
    public List<UserExercise> findByUserId(int userId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<UserExercise> userExercises = new LinkedList<>();

        String query = "SELECT * FROM UserExercises WHERE user_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserExercise userExercise = UserExercise.builder()
                        .userId(resultSet.getInt("user_id"))
                        .exerciseId(resultSet.getInt("exercise_id"))
                        .exerciseDate(resultSet.getDate("exercise_date"))
                        .duration(resultSet.getInt("duration"))
                        .build();

                userExercises.add(userExercise);
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

        return userExercises;
    }

    @Override
    public void save(UserExercise userExercise) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String querySelect = "SELECT user_id FROM UserExercises WHERE user_id = ? AND exercise_id = ? AND exercise_date = ?";

        String queryInsert = "INSERT INTO UserExercises (user_id, exercise_id, exercise_date, duration) VALUES (?, ?, ?, ?);";

        try (PreparedStatement selectStatement = con.prepareStatement(querySelect)) {
            selectStatement.setInt(1, userExercise.getUserId());
            selectStatement.setInt(2, userExercise.getExerciseId());
            selectStatement.setDate(3, new java.sql.Date(userExercise.getExerciseDate().getTime()));

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Row already exists, update duration
                String queryUpdate = "UPDATE UserExercises SET duration = ? WHERE user_id = ? AND exercise_id = ? AND exercise_date = ?";
                try (PreparedStatement updateStatement = con.prepareStatement(queryUpdate)) {
                    updateStatement.setInt(1, userExercise.getDuration());
                    updateStatement.setInt(2, userExercise.getUserId());
                    updateStatement.setInt(3, userExercise.getExerciseId());
                    updateStatement.setDate(4, new java.sql.Date(userExercise.getExerciseDate().getTime()));

                    updateStatement.executeUpdate();
                }
            } else {
                // Row doesn't exist, insert new row
                try (PreparedStatement insertStatement = con.prepareStatement(queryInsert)) {
                    insertStatement.setInt(1, userExercise.getUserId());
                    insertStatement.setInt(2, userExercise.getExerciseId());
                    insertStatement.setDate(3, new java.sql.Date(userExercise.getExerciseDate().getTime()));
                    insertStatement.setInt(4, userExercise.getDuration());

                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void deleteByUserIdAndExerciseIdAndExerciseDate(int userId, int exerciseId, Date exerciseDate) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "DELETE FROM UserExercises WHERE user_id=? AND exercise_id=? AND exercise_date=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, exerciseId);
            preparedStatement.setDate(3, new java.sql.Date(exerciseDate.getTime()));

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
