package com.example.healthtrackingsystem.Interfaces;

import com.example.healthtrackingsystem.Models.Exercise;

import java.util.List;

public interface ExerciseDao {
    List<Exercise> findAll();
    List<Exercise> findByExerciseType(String exerciseType);

    Exercise findById(int exerciseId);

    void save(Exercise exercise);
    Exercise findOneByExerciseName(String exerciseName);
    void deleteById(int exerciseId);
}
