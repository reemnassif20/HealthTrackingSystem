package com.example.healthtrackingsystem.dao;
import com.example.healthtrackingsystem.Interfaces.FoodDao;
import com.example.healthtrackingsystem.Models.Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {

    @Override
    public List<Food> findAll() {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<Food> foods = new LinkedList<>();

        String query = "SELECT * FROM Food;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Food food = Food.builder()
                        .foodId(resultSet.getInt("food_id"))
                        .foodName(resultSet.getString("food_name"))
                        .foodType(resultSet.getString("food_type"))
                        .caloriesPerHundredUnits(resultSet.getInt("calories_per_hundred_units"))
                        .build();

                foods.add(food);
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

        return foods;
    }

    @Override
    public Food findById(int foodId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM Food WHERE food_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, foodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Food.builder()
                        .foodId(resultSet.getInt("food_id"))
                        .foodName(resultSet.getString("food_name"))
                        .foodType(resultSet.getString("food_type"))
                        .caloriesPerHundredUnits(resultSet.getInt("calories_per_hundred_units"))
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
    public void save(Food food) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        if (food.getFoodId() > 0) { // Update
            String query = "UPDATE Food SET food_name=?, food_type=?, calories_per_hundred_units=? WHERE food_id=?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, food.getFoodName());
                preparedStatement.setString(2, food.getFoodType());
                preparedStatement.setInt(3, food.getCaloriesPerHundredUnits());
                preparedStatement.setInt(4, food.getFoodId());
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
            String query = "INSERT INTO Food (food_name, food_type, calories_per_hundred_units) VALUES (?, ?, ?);";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, food.getFoodName());
                preparedStatement.setString(2, food.getFoodType());
                preparedStatement.setInt(3, food.getCaloriesPerHundredUnits());
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
    public void deleteById(int foodId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "DELETE FROM Food WHERE food_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, foodId);

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
    public Food findByFoodName(String foodName) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM food WHERE food_name = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, foodName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int foodId = resultSet.getInt("food_id");
                String foodType = resultSet.getString("food_type");
                int caloriesPerHundredUnits = resultSet.getInt("calories_per_hundred_units");

                return new Food(foodId, foodName, foodType, caloriesPerHundredUnits);
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
        return null;
    }


    @Override
    public List<Food> findByFoodType(String foodType) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<Food> foods = new LinkedList<>();

        String query = "SELECT * FROM Food WHERE food_type=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, foodType);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Food food = Food.builder()
                        .foodId(resultSet.getInt("food_id"))
                        .foodName(resultSet.getString("food_name"))
                        .foodType(resultSet.getString("food_type"))
                        .caloriesPerHundredUnits(resultSet.getInt("calories_per_hundred_units"))
                        .build();

                foods.add(food);
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

        return foods;
    }
}
