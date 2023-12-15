package com.example.healthtrackingsystem.Models;


    public class Exercise {
        private int exerciseId;
        private String exerciseType;
        private String exerciseName;
        private int activityTypeCalories;

        public Exercise() {
        }

        public Exercise(int exerciseId, String exerciseType, String exerciseName, int activityTypeCalories) {
            this.exerciseId = exerciseId;
            this.exerciseType = exerciseType;
            this.exerciseName = exerciseName;
            this.activityTypeCalories = activityTypeCalories;
        }

        public int getExerciseId() {
            return exerciseId;
        }

        public void setExerciseId(int exerciseId) {
            this.exerciseId = exerciseId;
        }

        public String getExerciseType() {
            return exerciseType;
        }

        public void setExerciseType(String exerciseType) {
            this.exerciseType = exerciseType;
        }

        public String getExerciseName() {
            return exerciseName;
        }

        public void setExerciseName(String exerciseName) {
            this.exerciseName = exerciseName;
        }

        public int getActivityTypeCalories() {
            return activityTypeCalories;
        }

        public void setActivityTypeCalories(int activityTypeCalories) {
            this.activityTypeCalories = activityTypeCalories;
        }

        public static ExerciseBuilder builder() {
            return new ExerciseBuilder();
        }

        @Override
        public String toString() {
            return "Exercise{" +
                    "exerciseId=" + exerciseId +
                    ", exerciseType='" + exerciseType + '\'' +
                    ", exerciseName='" + exerciseName + '\'' +
                    ", activityTypeCalories=" + activityTypeCalories +
                    '}';
        }
    }

