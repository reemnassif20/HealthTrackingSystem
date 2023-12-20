package com.example.healthtrackingsystem.Interfaces;

import com.example.healthtrackingsystem.Models.UserFood;

import java.util.Date;
import java.util.List;

public interface UserFoodDao {
    List<UserFood> findAll();
    List<UserFood> findByUserId(int userId);

    void save(UserFood userFood);
    List<UserFood> findByUserIdAndDate(int userId, Date foodDate);
    double calculateTotalCalories(int userId, Date foodDate) ;
    void deleteByUserIdAndFoodIdAndFoodDate(int userId, int foodId, Date foodDate);
}
