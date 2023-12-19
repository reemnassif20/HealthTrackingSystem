package com.example.healthtrackingsystem.dao;

import com.example.healthtrackingsystem.Interfaces.FoodDao;
import com.example.healthtrackingsystem.Interfaces.UserFoodDao;
import com.example.healthtrackingsystem.Models.Food;
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

        String querySelect = "SELECT user_id FROM UserFood WHERE user_id = ? AND food_id = ? AND meal_type = ? AND food_date = ?";

        String queryInsert = "INSERT INTO UserFood (user_id, food_id, meal_type, quantity, food_date) VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement selectStatement = con.prepareStatement(querySelect)) {
            selectStatement.setInt(1, userFood.getUserId());
            selectStatement.setInt(2, userFood.getFoodId());
            selectStatement.setString(3, userFood.getMealType());
            selectStatement.setDate(4, new java.sql.Date(userFood.getFoodDate().getTime()));

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Row already exists, update quantity
                String queryUpdate = "UPDATE UserFood SET quantity = quantity + ? WHERE user_id = ? AND food_id = ? AND meal_type = ? AND food_date = ?";
                try (PreparedStatement updateStatement = con.prepareStatement(queryUpdate)) {
                    updateStatement.setInt(1, userFood.getQuantity());
                    updateStatement.setInt(2, userFood.getUserId());
                    updateStatement.setInt(3, userFood.getFoodId());
                    updateStatement.setString(4, userFood.getMealType());
                    updateStatement.setDate(5, new java.sql.Date(userFood.getFoodDate().getTime()));

                    updateStatement.executeUpdate();
                }
            } else {
                // Row doesn't exist, insert new row
                try (PreparedStatement insertStatement = con.prepareStatement(queryInsert)) {
                    insertStatement.setInt(1, userFood.getUserId());
                    insertStatement.setInt(2, userFood.getFoodId());
                    insertStatement.setString(3, userFood.getMealType());
                    insertStatement.setInt(4, userFood.getQuantity());
                    insertStatement.setDate(5, new java.sql.Date(userFood.getFoodDate().getTime()));

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
    @Override
    public List<UserFood> findByUserIdAndDate(int userId, Date foodDate) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<UserFood> userFoods = new LinkedList<>();

        String query = "SELECT * FROM UserFood WHERE user_id=? AND food_date=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, new java.sql.Date(foodDate.getTime()));

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
    public double calculateTotalCalories(int userId, Date foodDate) {
        List<UserFood> userFoods = findByUserIdAndDate(userId, foodDate);
        double totalCalories = 0;

        if (userFoods != null) {
            for (UserFood userFood : userFoods) {
                FoodDao foodDao = new FoodDaoImpl();
                Food food = foodDao.findById(userFood.getFoodId());

                if (food != null) {
                    double calories = food.getCaloriesPerHundredUnits();
                    totalCalories += calories * (userFood.getQuantity() / 100.0);
                }
            }
        }

        return totalCalories;
    }



}
