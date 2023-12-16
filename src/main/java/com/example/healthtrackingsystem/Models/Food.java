package com.example.healthtrackingsystem.Models;

public class Food {
    private int foodId;
    private String foodName;
    private String foodType;
    private int caloriesPerHundredUnits;

    public Food(int foodId, String foodName, String foodType, int caloriesPerHundredUnits) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodType = foodType;
        this.caloriesPerHundredUnits = caloriesPerHundredUnits;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getCaloriesPerHundredUnits() {
        return caloriesPerHundredUnits;
    }

    public void setCaloriesPerHundredUnits(int caloriesPerHundredUnits) {
        this.caloriesPerHundredUnits = caloriesPerHundredUnits;
    }
    public static FoodBuilder builder() {
        return new FoodBuilder();
    }
}
