package com.example.healthtrackingsystem.Models;

import java.util.Date;

public class UserExercise {
    private int userId;
    private int exerciseId;
    private Date exerciseDate;
    private Integer duration;

    public UserExercise(int userId, int exerciseId, Date exerciseDate, Integer duration) {
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.exerciseDate = exerciseDate;
        this.duration = duration;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public static UserExerciseBuilder builder() {
        return new UserExerciseBuilder();
    }
}
