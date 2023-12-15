package com.example.healthtrackingsystem.dao;

import com.example.healthtrackingsystem.Interfaces.ExerciseDao;
import com.example.healthtrackingsystem.Models.Exercise;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ExerciseDaoImpl implements ExerciseDao {

    @Override
    public List<Exercise> findAll() {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<Exercise> exercises = new LinkedList<>();

        String query = "SELECT * FROM Exercise;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = Exercise.builder()
                        .exerciseId(resultSet.getInt("exercise_id"))
                        .exerciseType(resultSet.getString("exercise_type"))
                        .exerciseName(resultSet.getString("exercise_name"))
                        .activityTypeCalories(resultSet.getInt("activity_type_calories"))
                        .build();

                exercises.add(exercise);
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

        return exercises;
    }

    @Override
    public Exercise findById(int exerciseId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM Exercise WHERE exercise_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, exerciseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Exercise.builder()
                        .exerciseId(resultSet.getInt("exercise_id"))
                        .exerciseType(resultSet.getString("exercise_type"))
                        .exerciseName(resultSet.getString("exercise_name"))
                        .activityTypeCalories(resultSet.getInt("activity_type_calories"))
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
    public void save(Exercise exercise) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        if (exercise.getExerciseId() > 0) { // Update
            String query = "UPDATE Exercise SET exercise_type=?, exercise_name=?, activity_type_calories=? WHERE exercise_id=?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, exercise.getExerciseType());
                preparedStatement.setString(2, exercise.getExerciseName());
                preparedStatement.setInt(3, exercise.getActivityTypeCalories());
                preparedStatement.setInt(4, exercise.getExerciseId());
                preparedStatement.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } else { // Create
            String query = "INSERT INTO Exercise (exercise_type, exercise_name, activity_type_calories) VALUES (?, ?, ?);";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, exercise.getExerciseType());
                preparedStatement.setString(2, exercise.getExerciseName());
                preparedStatement.setInt(3, exercise.getActivityTypeCalories());
                preparedStatement.executeUpdate();
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
    }

    @Override
    public void deleteById(int exerciseId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "DELETE FROM Exercise WHERE exercise_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, exerciseId);

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
