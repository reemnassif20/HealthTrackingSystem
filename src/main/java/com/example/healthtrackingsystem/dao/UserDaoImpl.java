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
            String query = "UPDATE User SET username=?, password=?, email=?, gender=?, current_weight=?, age=?, height=?, registration_date=? WHERE user_id=?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getGender());
                preparedStatement.setBigDecimal(5, user.getCurrentWeight());
                preparedStatement.setInt(6, user.getAge());
                preparedStatement.setInt(7, user.getHeight());
                preparedStatement.setInt(8, user.getUserId());
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
        } else { // Create
            String query = "INSERT INTO User (username, password, email, gender, current_weight, age, height, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getGender());
                preparedStatement.setBigDecimal(5, user.getCurrentWeight());
                preparedStatement.setInt(6, user.getAge());
                preparedStatement.setInt(7, user.getHeight());
                java.sql.Date sqlRegistrationDate = new java.sql.Date(user.getRegistrationDate().getTime());
                preparedStatement.setDate(8, sqlRegistrationDate);
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
    @Override
    public User getUserByEmail(String email) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM User WHERE email=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return User.builder()
                        .userId(resultSet.getInt("user_id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
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
    public void updateUsername(int userId, String newUsername) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "UPDATE User SET username=? WHERE user_id=?;";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, newUsername);
            preparedStatement.setInt(2, userId);
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
    public void updatePassword(int userId, String newPassword) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "UPDATE User SET password=? WHERE user_id=?;";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userId);
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
