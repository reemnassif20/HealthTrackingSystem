package com.example.healthtrackingsystem.Models;

import java.util.Date;

public class UserFoodBuilder {
    private int userId;
    private int foodId;
    private String mealType;
    private Integer quantity;
    private Date foodDate;

    public UserFoodBuilder userId(int userId) {
        this.userId = userId;
        return this;
    }

    public UserFoodBuilder foodId(int foodId) {
        this.foodId = foodId;
        return this;
    }

    public UserFoodBuilder mealType(String mealType) {
        this.mealType = mealType;
        return this;
    }

    public UserFoodBuilder quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public UserFoodBuilder foodDate(Date foodDate) {
        this.foodDate = foodDate;
        return this;
    }

    public UserFood build() {
        return new UserFood(userId, foodId, mealType, quantity, foodDate);
    }
}
