package com.example.healthtrackingsystem.Controllers;

import com.example.healthtrackingsystem.Interfaces.FoodDao;
import com.example.healthtrackingsystem.Models.Food;
import com.example.healthtrackingsystem.Models.User;
import com.example.healthtrackingsystem.dao.FoodDaoImpl;
import com.example.healthtrackingsystem.dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
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

    private String breakFastFoodValue;
    private String breakFastDessertValue;
    private String breakFastDrinkValue;
    private String lunchFoodValue;
    private String lunchDessertValue;
    private String lunchDrinkValue;
    private String dinnerFoodValue;
    private String dinnerDessertValue;
    private String dinnerDrinkValue;




    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        datePicker.setOnAction(this::handleDatePickerAction);



        updateDessertsComboBox();
        updateFoodComboBox();
        updateDrinksComboBox();

    }
    private void handleDatePickerAction(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();
        System.out.println("Selected date: " + selectedDate);
    }


    @FXML
    private void handleBreakFastFoodSelection() {
        breakFastFoodValue = breakFastFoodComboBox.getValue();
        String breakFastFoodQuantity=breakFastFood.getText();
        System.out.println("Selected Value for Breakfast Food: " + breakFastFoodValue );
        System.out.println("Selected Value for Breakfast breakFastDrinkQuantity: " + breakFastFoodQuantity );
    }

    @FXML
    private void handleBreakFastDessertSelection() {
        breakFastDessertValue = breakFastDessertComboBox.getValue();
        String breakFastDessertQuantity=breakFastDessert.getText();
        System.out.println("Selected Value for Breakfast Dessert: " + breakFastDessertValue+ breakFastDessertQuantity);
    }
    @FXML
    private void handleBreakFastDrinkSelection() {
        breakFastDrinkValue = breakFastDrinkComboBox.getValue();
        String breakFastDrinkQuantity=breakFastDrink.getText();
        System.out.println("Selected Value for Breakfast Drink: " + breakFastDrinkValue+ breakFastDrinkQuantity);
    }

    @FXML
    private void handleLunchFoodSelection() {
        lunchFoodValue = lunchFoodComboBox.getValue();
        String lunchFoodQuantity=lunchFood.getText();

        System.out.println("Selected Value for Lunch Food: " + lunchFoodValue+lunchFoodQuantity);
    }

    @FXML
    private void handleLunchDessertSelection() {
        lunchDessertValue = lunchDessertComboBox.getValue();
        String lunchDessertQuantity=lunchDessert.getText();

        System.out.println("Selected Value for Lunch Dessert: " + lunchDessertValue+lunchDessertQuantity);
    }

    @FXML
    private void handleLunchDrinkSelection() {
        lunchDrinkValue = lunchDrinkComboBox.getValue();
        String lunchDrinkQuantity=lunchDrink.getText();

        System.out.println("Selected Value for Lunch Drink: " + lunchDrinkValue+lunchDrinkQuantity);
    }

    @FXML
    private void handleDinnerFoodSelection() {
        dinnerFoodValue = dinnerFoodComboBox.getValue();
        String dinnerFoodQuantity=dinnerFood.getText();

        System.out.println("Selected Value for Dinner Food: " + dinnerFoodValue+dinnerFoodQuantity);
    }

    @FXML
    private void handleDinnerDessertSelection() {
        dinnerDessertValue = dinnerDessertComboBox.getValue();
        String dinnerDessertQuantity=dinnerDessert.getText();

        System.out.println("Selected Value for Dinner Dessert: " + dinnerDessertValue+dinnerDessertQuantity);
    }

    @FXML
    private void handleDinnerDrinkSelection() {
        dinnerDrinkValue = dinnerDrinkComboBox.getValue();
        String dinnerDrinkQuantity=dinnerDrink.getText();
        System.out.println("Selected Value for Dinner Drink: " + dinnerDrinkValue+dinnerDrinkQuantity);
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
