package com.example.healthtrackingsystem.dao;
import com.example.healthtrackingsystem.Interfaces.UserDao;
import com.example.healthtrackingsystem.Models.User;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<User> users = new LinkedList<>();

        String query = "SELECT * FROM User;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .userId(resultSet.getInt("user_id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .fullName(resultSet.getString("full_name"))
                        .email(resultSet.getString("email"))
                        .gender(resultSet.getString("gender"))
                        .currentWeight(resultSet.getBigDecimal("current_weight"))
                        .age(resultSet.getInt("age"))
                        .height(resultSet.getInt("height"))
                        .registrationDate(resultSet.getDate("registration_date"))
                        .build();

                users.add(user);
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

        return users;
    }

    @Override
    public User findById(int userId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM User WHERE user_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return User.builder()
                        .userId(resultSet.getInt("user_id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .fullName(resultSet.getString("full_name"))
                        .email(resultSet.getString("email"))
                        .gender(resultSet.getString("gender"))
                        .currentWeight(resultSet.getBigDecimal("current_weight"))
                        .age(resultSet.getInt("age"))
                        .height(resultSet.getInt("height"))
                        .registrationDate(resultSet.getDate("registration_date"))
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
    public void save(User user) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        if (user.getUserId() > 0) { // Update
            String query = "UPDATE User SET username=?, password=?, full_name=?, email=?, gender=?, current_weight=?, age=?, height=?, registration_date=? WHERE user_id=?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFullName());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getGender());
                preparedStatement.setBigDecimal(6, user.getCurrentWeight());
                preparedStatement.setInt(7, user.getAge());
                preparedStatement.setInt(8, user.getHeight());
                preparedStatement.setInt(9, user.getUserId());
                java.sql.Date sqlRegistrationDate = new java.sql.Date(user.getRegistrationDate().getTime());
                preparedStatement.setDate(10, sqlRegistrationDate);
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
            String query = "INSERT INTO User (username, password, full_name, email, gender, current_weight, age, height, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFullName());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getGender());
                preparedStatement.setBigDecimal(6, user.getCurrentWeight());
                preparedStatement.setInt(7, user.getAge());
                preparedStatement.setInt(8, user.getHeight());
                java.sql.Date sqlRegistrationDate = new java.sql.Date(user.getRegistrationDate().getTime());
                preparedStatement.setDate(9, sqlRegistrationDate);
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
    public void deleteById(int userId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "DELETE FROM User WHERE user_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);

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
