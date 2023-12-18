package com.example.healthtrackingsystem.Models;

import java.util.Date;

public class UserExerciseBuilder {
    private int userId;
    private int exerciseId;
    private Date exerciseDate;
    private Integer duration;

    public UserExerciseBuilder userId(int userId) {
        this.userId = userId;
        return this;
    }

    public UserExerciseBuilder exerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
        return this;
    }

    public UserExerciseBuilder exerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
        return this;
    }

    public UserExerciseBuilder duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public UserExercise build() {
        return new UserExercise(userId, exerciseId, exerciseDate, duration);
    }
}
