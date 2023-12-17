package com.example.healthtrackingsystem.Models;

import java.math.BigDecimal;
import java.util.Date;

public class History {
    private int historyId;
    private Date historyDate;
    private BigDecimal historyWeight;
    private int userId;

    public History() {
    }

    public History(int historyId, Date historyDate, BigDecimal historyWeight, int userId) {
        this.historyId = historyId;
        this.historyDate = historyDate;
        this.historyWeight = historyWeight;
        this.userId = userId;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }

    public BigDecimal getHistoryWeight() {
        return historyWeight;
    }

    public void setHistoryWeight(BigDecimal historyWeight) {
        this.historyWeight = historyWeight;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static HistoryBuilder builder() {
        return new HistoryBuilder();
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", historyDate=" + historyDate +
                ", historyWeight=" + historyWeight +
                ", userId=" + userId +
                '}';
    }
}
