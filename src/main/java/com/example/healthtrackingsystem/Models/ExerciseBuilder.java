package com.example.healthtrackingsystem.Models;
public class ExerciseBuilder {
    private int exerciseId;
    private String exerciseType;
    private String exerciseName;
    private int activityTypeCalories;

    public ExerciseBuilder exerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
        return this;
    }

    public ExerciseBuilder exerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
        return this;
    }

    public ExerciseBuilder exerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public ExerciseBuilder activityTypeCalories(int activityTypeCalories) {
        this.activityTypeCalories = activityTypeCalories;
        return this;
    }

    public Exercise build() {
        return new Exercise(exerciseId, exerciseType, exerciseName, activityTypeCalories);
    }
}