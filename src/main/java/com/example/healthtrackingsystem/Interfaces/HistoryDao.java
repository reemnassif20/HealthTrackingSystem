package com.example.healthtrackingsystem.Interfaces;

import com.example.healthtrackingsystem.Models.History;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface HistoryDao {
    List<History> findAllByUserId(int userId);

    History findById(int historyId);

    void save(History history);

    void updateWeight(int historyId, BigDecimal newWeight);
    History findLatestHistoryByUserId(int userId);

    void deleteById(int historyId);

}
