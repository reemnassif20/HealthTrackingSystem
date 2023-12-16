package com.example.healthtrackingsystem.dao;

import com.example.healthtrackingsystem.Interfaces.UserFoodDao;
import com.example.healthtrackingsystem.Models.UserFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UserFoodDaoImpl implements UserFoodDao {

    @Override
    public List<UserFood> findAll() {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<UserFood> userFoods = new LinkedList<>();

        String query = "SELECT * FROM UserFood;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserFood userFood = UserFood.builder()
                        .userId(resultSet.getInt("user_id"))
                        .foodId(resultSet.getInt("food_id"))
                        .mealType(resultSet.getString("meal_type"))
                        .quantity(resultSet.getInt("quantity"))
                        .foodDate(resultSet.getDate("food_date"))
                        .build();

                userFoods.add(userFood);
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

        return userFoods;
    }

    @Override
    public UserFood findByUserIdAndFoodIdAndFoodDate(int userId, int foodId, Date foodDate) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM UserFood WHERE user_id=? AND food_id=? AND food_date=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, foodId);
            preparedStatement.setDate(3, new java.sql.Date(foodDate.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return UserFood.builder()
                        .userId(resultSet.getInt("user_id"))
                        .foodId(resultSet.getInt("food_id"))
                        .mealType(resultSet.getString("meal_type"))
                        .quantity(resultSet.getInt("quantity"))
                        .foodDate(resultSet.getDate("food_date"))
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
    public List<UserFood> findByUserId(int userId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<UserFood> userFoods = new LinkedList<>();

        String query = "SELECT * FROM UserFood WHERE user_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserFood userFood = UserFood.builder()
                        .userId(resultSet.getInt("user_id"))
                        .foodId(resultSet.getInt("food_id"))
                        .mealType(resultSet.getString("meal_type"))
                        .quantity(resultSet.getInt("quantity"))
                        .foodDate(resultSet.getDate("food_date"))
                        .build();

                userFoods.add(userFood);
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

        return userFoods;
    }

    @Override
    public void save(UserFood userFood) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "INSERT INTO UserFood (user_id, food_id, meal_type, quantity, food_date) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, userFood.getUserId());
            preparedStatement.setInt(2, userFood.getFoodId());
            preparedStatement.setString(3, userFood.getMealType());
            preparedStatement.setInt(4, userFood.getQuantity());
            preparedStatement.setDate(5, new java.sql.Date(userFood.getFoodDate().getTime()));

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

    @Override
    public void deleteByUserIdAndFoodIdAndFoodDate(int userId, int foodId, Date foodDate) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "DELETE FROM UserFood WHERE user_id=? AND food_id=? AND food_date=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, foodId);
            preparedStatement.setDate(3, new java.sql.Date(foodDate.getTime()));

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
