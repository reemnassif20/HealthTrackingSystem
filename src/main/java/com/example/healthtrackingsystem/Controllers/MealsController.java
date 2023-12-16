package com.example.healthtrackingsystem.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

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

    @FXML
    private Button breakFastFoodButton;

    @FXML
    private Button breakFastDessertButton;
    @FXML
    private Button breakFastDrinkButton;
    @FXML
    private Button lunchFoodButton;
    @FXML
    private Button lunchDessertButton;
    @FXML
    private Button lunchDrinkButton;
    @FXML
    private Button dinnerFoodButton;
    @FXML
    private Button dinnerDessertButton;
    @FXML
    private Button dinnerDrinkButton;

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

        initializeBreakFastFoodComboBox();
        initializeBreakFastDessertComboBox();
        initializeBreakFastDrinkComboBox();
        initializeLunchFoodComboBox();
        initializeLunchDessertComboBox();
        initializeLunchDrinkComboBox();
        initializeDinnerFoodComboBox();
        initializeDinnerDessertComboBox();
        initializeDinnerDrinkComboBox();
    }
    private void handleDatePickerAction(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();
        System.out.println("Selected date: " + selectedDate);
    }

    private void initializeBreakFastFoodComboBox() {
        breakFastFoodComboBox.getItems().addAll("x1", "x2", "x3");
        breakFastFoodComboBox.getSelectionModel().selectFirst();
    }

    private void initializeBreakFastDessertComboBox() {
        breakFastDessertComboBox.getItems().addAll("y1", "y2", "y3");
        breakFastDessertComboBox.getSelectionModel().selectFirst();
    }

    private void initializeBreakFastDrinkComboBox() {
        breakFastDrinkComboBox.getItems().addAll("z1", "z2", "z3");
        breakFastDrinkComboBox.getSelectionModel().selectFirst();
    }

    private void initializeLunchFoodComboBox() {
        lunchFoodComboBox.getItems().addAll("x4", "x5", "x6");
        lunchFoodComboBox.getSelectionModel().selectFirst();
    }

    private void initializeLunchDessertComboBox() {
        lunchDessertComboBox.getItems().addAll("y4", "y5", "y6");
        lunchDessertComboBox.getSelectionModel().selectFirst();
    }

    private void initializeLunchDrinkComboBox() {
        lunchDrinkComboBox.getItems().addAll("z4", "z5", "z6");
        lunchDrinkComboBox.getSelectionModel().selectFirst();
    }

    private void initializeDinnerFoodComboBox() {
        dinnerFoodComboBox.getItems().addAll("x7", "x8", "x9");
        dinnerFoodComboBox.getSelectionModel().selectFirst();
    }

    private void initializeDinnerDessertComboBox() {
        dinnerDessertComboBox.getItems().addAll("y7", "y8", "y9");
        dinnerDessertComboBox.getSelectionModel().selectFirst();
    }

    private void initializeDinnerDrinkComboBox() {
        dinnerDrinkComboBox.getItems().addAll("z7", "z8", "z9");
        dinnerDrinkComboBox.getSelectionModel().selectFirst();
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






}
