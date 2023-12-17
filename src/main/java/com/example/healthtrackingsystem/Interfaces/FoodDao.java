package com.example.healthtrackingsystem.Interfaces;

import com.example.healthtrackingsystem.Models.Food;

import java.util.List;

public interface FoodDao {
    List<Food> findAll();
    List<Food> findByFoodType(String foodType);

    Food findById(int foodId);
    Food findByFoodName(String foodName);
    void save(Food food);

    void deleteById(int foodId);
}
