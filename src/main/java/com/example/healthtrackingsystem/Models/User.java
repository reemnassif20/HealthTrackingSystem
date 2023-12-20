package com.example.healthtrackingsystem.Models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class User {
    private int userId;
    private String username;
    private String password;

    private String email;
    private String gender;
    private BigDecimal currentWeight;
    private Date registrationDate;
    private int age;
    private int height;

    public User() {
    }

    public User(int userId, String username, String password, String email,
                String gender, BigDecimal currentWeight, int age, int height,Date registrationDate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.currentWeight = currentWeight;
        this.age = age;
        this.height = height;
        this.registrationDate = registrationDate;

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigDecimal getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(BigDecimal currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    public double calculateBMI() {
        // BMI calculation: BMI = weight (kg) / (height (m))^2
        double heightInMeters = height / 100.0; // Convert height to meters
        return Math.round((currentWeight.doubleValue() / (heightInMeters * heightInMeters)) * 100.0) / 100.0;
    }


    public BigDecimal calculateOptimalWeight() {
        // Optimal weight calculation (e.g., BMI of 22)
        double optimalBMI = 22.0;
        double optimalWeight = optimalBMI * (height / 100.0) * (height / 100.0);

        BigDecimal optimalWeightBigDecimal = BigDecimal.valueOf(optimalWeight);
        return optimalWeightBigDecimal.setScale(2, RoundingMode.HALF_UP);
    }

    public String determineWeightStatus() {
        double bmi = calculateBMI();

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public BigDecimal calculateWeightToLoseOrGain() {
        BigDecimal optimalWeight = calculateOptimalWeight();
        BigDecimal weightDifference = optimalWeight.subtract(currentWeight).abs();
        return weightDifference.setScale(2, RoundingMode.HALF_UP);
    }
    public String determineWeightChangeRecommendation() {
        double bmi = calculateBMI();

        // Define BMI ranges for recommendations
        double normalWeightLowerThreshold = 18.5;


        if (bmi < normalWeightLowerThreshold) {
            return "gain";
        } else {
            return "lose";
        }
    }


    public int calculateCaloriesPerDay() {
        // Example: Calculate calories needed for weight management

        // Base calories needed for weight maintenance
        int baseCalories = 2000;

        // Adjust calories based on weight change goal (lose, maintain, gain)
        double weightChangeMultiplier = getWeightChangeMultiplier();
        int adjustedCalories = (int) (baseCalories * weightChangeMultiplier);

        // Adjust calories based on other factors (gender, activity level, etc.)
        double genderMultiplier = getGenderMultiplier();
        double activityLevelMultiplier = 1;

        // Combine all multipliers to get the final adjusted calories
        int finalCalories = (int) (adjustedCalories * genderMultiplier * activityLevelMultiplier);

        return finalCalories;
    }

    private double getWeightChangeMultiplier() {
        String weightChangeRecommendation = determineWeightChangeRecommendation();

        // Adjust based on weight change goal
        switch (weightChangeRecommendation) {
            case "gain":
                return 1.2; // Increase calories for weight gain
            case "lose":
                return 0.8; // Decrease calories for weight loss
            default:
                return 1.0; // Maintain calories for weight maintenance
        }
    }

    private double getGenderMultiplier() {
        // Adjust based on gender
        if ("Male".equalsIgnoreCase(gender)) {
            return 1.1; // Adjust for male gender
        } else {
            return 1.0; //adjustment for female genders
        }
    }



    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", currentWeight=" + currentWeight +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
