package com.example.healthtrackingsystem.Interfaces;

import com.example.healthtrackingsystem.Models.Exercise;

import java.util.List;

public interface ExerciseDao {
    List<Exercise> findAll();

    Exercise findById(int exerciseId);

    void save(Exercise exercise);

    void deleteById(int exerciseId);
}
