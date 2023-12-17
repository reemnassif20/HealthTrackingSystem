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
    public List<History> findAllByUserIdAndDate(int userId, Date historyDate) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }

        List<History> histories = new ArrayList<>();

        String query = "SELECT * FROM history WHERE user_id = ? AND history_date = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, new java.sql.Date(historyDate.getTime()));

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

        if (history.getHistoryId() > 0) { // Update
            String query = "UPDATE history SET history_date=?, history_weight=? WHERE history_id=?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setDate(1, new java.sql.Date(history.getHistoryDate().getTime()));
                preparedStatement.setBigDecimal(2, history.getHistoryWeight());
                preparedStatement.setInt(3, history.getHistoryId());

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
}
