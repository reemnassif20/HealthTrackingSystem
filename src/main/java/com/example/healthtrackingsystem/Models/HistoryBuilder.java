package com.example.healthtrackingsystem.Models;

import java.math.BigDecimal;
import java.util.Date;

public class HistoryBuilder {
    private int historyId;
    private Date historyDate;
    private BigDecimal historyWeight;
    private int userId;

    public HistoryBuilder historyId(int historyId) {
        this.historyId = historyId;
        return this;
    }

    public HistoryBuilder historyDate(Date historyDate) {
        this.historyDate = historyDate;
        return this;
    }

    public HistoryBuilder historyWeight(BigDecimal historyWeight) {
        this.historyWeight = historyWeight;
        return this;
    }

    public HistoryBuilder userId(int userId) {
        this.userId = userId;
        return this;
    }

    public History build() {
        return new History(historyId, historyDate, historyWeight, userId);
    }
}
