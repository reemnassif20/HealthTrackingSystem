package com.example.healthtrackingsystem.Interfaces;

import com.example.healthtrackingsystem.Models.UserFood;

import java.util.Date;
import java.util.List;

public interface UserFoodDao {
    List<UserFood> findAll();

    UserFood findByUserIdAndFoodIdAndFoodDate(int userId, int foodId, Date foodDate);

    List<UserFood> findByUserId(int userId);

    void save(UserFood userFood);

    void deleteByUserIdAndFoodIdAndFoodDate(int userId, int foodId, Date foodDate);
}
