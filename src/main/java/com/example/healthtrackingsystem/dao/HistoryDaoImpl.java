package com.example.healthtrackingsystem.dao;
import com.example.healthtrackingsystem.Interfaces.HistoryDao;
import com.example.healthtrackingsystem.Models.History;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryDaoImpl implements HistoryDao {

    @Override
    public List<History> findAllByUserId(int userId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<History> histories = new ArrayList<>();

        String query = "SELECT * FROM history WHERE user_id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                History history = History.builder()
                        .historyId(resultSet.getInt("history_id"))
                        .historyDate(resultSet.getDate("history_date"))
                        .historyWeight(resultSet.getBigDecimal("history_weight"))
                        .userId(resultSet.getInt("user_id"))
                        .build();

                histories.add(history);
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

        return histories;
    }

    @Override
    public History findById(int historyId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM history WHERE history_id = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, historyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return History.builder()
                        .historyId(resultSet.getInt("history_id"))
                        .historyDate(resultSet.getDate("history_date"))
                        .historyWeight(resultSet.getBigDecimal("history_weight"))
                        .userId(resultSet.getInt("user_id"))
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
    public void save(History history) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        // Check if a record with the same date and user_id already exists
        if (recordExists(con, history.getUserId(), history.getHistoryDate())) {
            updateWeight(con, history);
        } else {
            createRecord(con, history);
        }
    }

    // Helper method to check if a record with the same date and user_id exists
    private boolean recordExists(Connection con, int userId, Date historyDate) {
        String query = "SELECT COUNT(*) FROM history WHERE user_id=? AND history_date=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, new java.sql.Date(historyDate.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }

    // Helper method to update the weight if a record with the same date and user_id exists
    private void updateWeight(Connection con, History history) {
        String query = "UPDATE history SET history_weight=? WHERE user_id=? AND history_date=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setBigDecimal(1, history.getHistoryWeight());
            preparedStatement.setInt(2, history.getUserId());
            preparedStatement.setDate(3, new java.sql.Date(history.getHistoryDate().getTime()));

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

    // Helper method to create a new record if no record with the same date and user_id exists
    private void createRecord(Connection con, History history) {
        String query = "INSERT INTO history (history_date, history_weight, user_id) VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(history.getHistoryDate().getTime()));
            preparedStatement.setBigDecimal(2, history.getHistoryWeight());
            preparedStatement.setInt(3, history.getUserId());

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
    public void updateWeight(int historyId, BigDecimal newWeight) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "UPDATE history SET history_weight=? WHERE history_id=?;";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setBigDecimal(1, newWeight);
            preparedStatement.setInt(2, historyId);
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
    public void deleteById(int historyId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }

        String query = "DELETE FROM history WHERE history_id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, historyId);

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
    public History findLatestHistoryByUserId(int userId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM history WHERE user_id=? ORDER BY history_date DESC LIMIT 1;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return History.builder()
                        .historyId(resultSet.getInt("history_id"))
                        .historyDate(resultSet.getDate("history_date"))
                        .historyWeight(resultSet.getBigDecimal("history_weight"))
                        .userId(resultSet.getInt("user_id"))
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


}
