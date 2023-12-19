package com.example.healthtrackingsystem.Interfaces;

import com.example.healthtrackingsystem.Models.UserExercise;

import java.util.Date;
import java.util.List;

public interface UserExerciseDao {
    List<UserExercise> findAll();

    UserExercise findByUserIdAndExerciseIdAndExerciseDate(int userId, int exerciseId, Date exerciseDate);

    List<UserExercise> findByUserId(int userId);

    void save(UserExercise userExercise);
    List<UserExercise> findByUserIdAndDate(int userId, Date exerciseDate);

    void deleteByUserIdAndExerciseIdAndExerciseDate(int userId, int exerciseId, Date exerciseDate);

    double calculateTotalBurnedCalories(int userId, Date exerciseDate);  








}
