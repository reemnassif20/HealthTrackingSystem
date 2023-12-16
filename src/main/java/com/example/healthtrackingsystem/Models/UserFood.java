package com.example.healthtrackingsystem.Models;

import java.util.Date;

public class UserFood {
    private int userId;
    private int foodId;
    private String mealType;
    private Integer quantity;
    private Date foodDate;

    public UserFood(int userId, int foodId, String mealType, Integer quantity, Date foodDate) {
        this.userId = userId;
        this.foodId = foodId;
        this.mealType = mealType;
        this.quantity = quantity;
        this.foodDate = foodDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getFoodDate() {
        return foodDate;
    }

    public void setFoodDate(Date foodDate) {
        this.foodDate = foodDate;
    }
    public static UserFoodBuilder builder() {
        return new UserFoodBuilder();
    }
}
