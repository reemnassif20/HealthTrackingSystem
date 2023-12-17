package com.example.healthtrackingsystem.Interfaces;

import com.example.healthtrackingsystem.Models.History;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface HistoryDao {
    List<History> findAllByUserIdAndDate(int userId, Date historyDate);

    History findById(int historyId);

    void save(History history);

    void updateWeight(int historyId, BigDecimal newWeight);

    void deleteById(int historyId);

}
