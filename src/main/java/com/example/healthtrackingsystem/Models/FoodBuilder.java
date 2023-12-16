package com.example.healthtrackingsystem.Models;

import java.util.Date;

public class FoodBuilder {
    private int foodId;
    private String foodName;
    private String foodType;
    private int caloriesPerHundredUnits;

    public FoodBuilder foodId(int foodId) {
        this.foodId = foodId;
        return this;
    }

    public FoodBuilder foodName(String foodName) {
        this.foodName = foodName;
        return this;
    }

    public FoodBuilder foodType(String foodType) {
        this.foodType = foodType;
        return this;
    }

    public FoodBuilder caloriesPerHundredUnits(int caloriesPerHundredUnits) {
        this.caloriesPerHundredUnits = caloriesPerHundredUnits;
        return this;
    }

    public Food build() {
        return new Food(foodId, foodName, foodType, caloriesPerHundredUnits);
    }
}
