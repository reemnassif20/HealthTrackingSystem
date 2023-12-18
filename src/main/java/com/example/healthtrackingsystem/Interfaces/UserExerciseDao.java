package com.example.healthtrackingsystem.Interfaces;

import com.example.healthtrackingsystem.Models.UserExercise;

import java.util.Date;
import java.util.List;

public interface UserExerciseDao {
    List<UserExercise> findAll();

    UserExercise findByUserIdAndExerciseIdAndExerciseDate(int userId, int exerciseId, Date exerciseDate);

    List<UserExercise> findByUserId(int userId);

    void save(UserExercise userExercise);

    void deleteByUserIdAndExerciseIdAndExerciseDate(int userId, int exerciseId, Date exerciseDate);
}
