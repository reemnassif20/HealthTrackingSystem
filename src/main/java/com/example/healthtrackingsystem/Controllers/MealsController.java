package com.example.healthtrackingsystem.Controllers;

import com.example.healthtrackingsystem.Interfaces.FoodDao;
import com.example.healthtrackingsystem.Models.Food;
import com.example.healthtrackingsystem.Models.UserFood;
import com.example.healthtrackingsystem.dao.FoodDaoImpl;
import com.example.healthtrackingsystem.dao.UserFoodDaoImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public class MealsController extends SceneController{

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField breakFastFood;
    @FXML
    private TextField breakFastDessert;
    @FXML
    private TextField breakFastDrink;
    @FXML
    private TextField lunchFood;
    @FXML
    private TextField lunchDessert;
    @FXML
    private TextField lunchDrink;
    @FXML
    private TextField dinnerFood;
    @FXML
    private TextField dinnerDessert;
    @FXML
    private TextField dinnerDrink;
    @FXML
    private Label successMessage;
    private Timeline successMessageTimeline;


    @FXML
    private ComboBox<String> breakFastFoodComboBox;

    @FXML
    private ComboBox<String> breakFastDessertComboBox;
    @FXML
    private ComboBox<String> breakFastDrinkComboBox;
    @FXML
    private ComboBox<String> lunchFoodComboBox;
    @FXML
    private ComboBox<String> lunchDessertComboBox;
    @FXML
    private ComboBox<String> lunchDrinkComboBox;
    @FXML
    private ComboBox<String> dinnerFoodComboBox;
    @FXML
    private ComboBox<String> dinnerDessertComboBox;
    @FXML
    private ComboBox<String> dinnerDrinkComboBox;
    @FXML
    private Label totalCaloriesLabel;
    private double totalCalories;



    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        datePicker.setOnAction(this::handleDatePickerAction);



        updateDessertsComboBox();
        updateFoodComboBox();
        updateDrinksComboBox();

        successMessageTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), this::hideSuccessMessage)
        );
        successMessageTimeline.setCycleCount(1);
        totalCalories = 0;

    }

    private void hideSuccessMessage(ActionEvent event) {
        successMessage.setText("");
    }

    private void showSuccessMessage() {
        successMessage.setText("Added Successfully!");
        successMessageTimeline.playFromStart();
    }
    private void handleDatePickerAction(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();
        System.out.println("Selected date: " + selectedDate);
    }

    private void handleMealSelection(String mealType, ComboBox<String> foodComboBox, TextField foodTextField) {
        String foodValue = foodComboBox.getValue();
        String foodQuantity = foodTextField.getText();

        FoodDaoImpl foodDao = new FoodDaoImpl();
        Food selectedFood = foodDao.findByFoodName(foodValue);

        int userId = UserRepository.getCurrentUser().getUserId();
        int foodId = selectedFood.getFoodId();
        int quantity = 0;
        try {
            quantity = Integer.parseInt(foodQuantity);
        } catch (NumberFormatException e) {
        }

        LocalDate foodDate = datePicker.getValue();
        Instant instant = foodDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);

        UserFood userFood = UserFood.builder()
                .userId(userId)
                .foodId(foodId)
                .mealType(mealType)
                .quantity(quantity)
                .foodDate(utilDate)
                .build();

        UserFoodDaoImpl userFoodDao = new UserFoodDaoImpl();
        userFoodDao.save(userFood);
        double calories = selectedFood.getCaloriesPerHundredUnits();
        double addedCalories = calories * (quantity / 100.0);
        totalCalories += addedCalories;
        totalCaloriesLabel.setText(String.valueOf(totalCalories));
        foodTextField.clear();
        showSuccessMessage();
    }

    @FXML
    private void handleBreakFastFoodSelection() {
        handleMealSelection("Breakfast", breakFastFoodComboBox, breakFastFood);
    }

    @FXML
    private void handleBreakFastDessertSelection() {
        handleMealSelection("Breakfast", breakFastDessertComboBox, breakFastDessert);
    }
    @FXML
    private void handleBreakFastDrinkSelection() {
        handleMealSelection("Breakfast", breakFastDrinkComboBox, breakFastDrink);
    }

    @FXML
    private void handleLunchFoodSelection() {
        handleMealSelection("Lunch", lunchFoodComboBox, lunchFood);
    }

    @FXML
    private void handleLunchDessertSelection() {
        handleMealSelection("Lunch", lunchDessertComboBox, lunchDessert);

    }

    @FXML
    private void handleLunchDrinkSelection() {
        handleMealSelection("Lunch", lunchDrinkComboBox, lunchDrink);

    }

    @FXML
    private void handleDinnerFoodSelection() {
        handleMealSelection("Dinner", dinnerFoodComboBox, dinnerFood);

    }

    @FXML
    private void handleDinnerDessertSelection() {
        handleMealSelection("Dinner", dinnerDessertComboBox, dinnerDessert);
    }

    @FXML
    private void handleDinnerDrinkSelection() {
        handleMealSelection("Dinner", dinnerDrinkComboBox, dinnerDrink);
    }



    private void updateComboBox(ComboBox<String> comboBox, List<Food> foodList) {
        comboBox.getItems().clear();
        for (Food food : foodList) {
            comboBox.getItems().add(food.getFoodName());
        }
        comboBox.getSelectionModel().selectFirst();
    }

    private void updateDessertsComboBox() {
        List<Food> dessertList = getDesserts();
        updateComboBox(breakFastDessertComboBox, dessertList);
        updateComboBox(lunchDessertComboBox, dessertList);
        updateComboBox(dinnerDessertComboBox, dessertList);
    }



    public List<Food> getDesserts() {
        FoodDao foodDao = new FoodDaoImpl();
        return foodDao.findByFoodType("Dessert");
    }

    private void updateFoodComboBox() {
        List<Food> foodList = getFood();
        updateComboBox(breakFastFoodComboBox, foodList);
        updateComboBox(lunchFoodComboBox, foodList);
        updateComboBox(dinnerFoodComboBox, foodList);
    }


    public List<Food> getFood() {
        FoodDao foodDao = new FoodDaoImpl();
        return foodDao.findByFoodType("Food");
    }
    private void updateDrinksComboBox() {
        List<Food> drinkList = getDrinks();
        updateComboBox(breakFastDrinkComboBox, drinkList);
        updateComboBox(lunchDrinkComboBox, drinkList);
        updateComboBox(dinnerDrinkComboBox, drinkList);
    }


    public List<Food> getDrinks() {
        FoodDao foodDao = new FoodDaoImpl();
        return foodDao.findByFoodType("Drink");
    }




}
