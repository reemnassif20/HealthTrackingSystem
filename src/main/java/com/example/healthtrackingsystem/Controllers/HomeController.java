package com.example.healthtrackingsystem.Controllers;
import com.example.healthtrackingsystem.Models.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.math.BigDecimal;


public class HomeController extends SceneController{
    @FXML
    private Text usernameText;

    @FXML
    private Text CurrentBMI;

    @FXML
    private Text CurrentWeight;

    @FXML
    private Text ChangeRecommendation;

    @FXML
    private Text WeightChange;

    @FXML
    private Text CaloriesPerDay;

    @FXML
    private Text WeightStatus;

    @FXML
    private Text OptimalWeight;


    private User signedInUser;

    @FXML
    public void initialize() {
        signedInUser = UserRepository.getCurrentUser();
        usernameText.setText(signedInUser.getUsername());


        double bmi = signedInUser.calculateBMI();
        BigDecimal weightToLoseOrGain = signedInUser.calculateWeightToLoseOrGain();
        int caloriesPerDay = signedInUser.calculateCaloriesPerDay();

        // Update UI elements
        CurrentBMI.setText(String.format("%.2f", bmi));
        CurrentWeight.setText(String.valueOf(signedInUser.getCurrentWeight()));
        ChangeRecommendation.setText(signedInUser.determineWeightChangeRecommendation());
        WeightChange.setText(weightToLoseOrGain.toString());
        CaloriesPerDay.setText(String.valueOf(caloriesPerDay));
        WeightStatus.setText(signedInUser.determineWeightStatus());
        OptimalWeight.setText(String.valueOf(signedInUser.calculateOptimalWeight()));

        System.out.println("BMI: " + signedInUser.calculateBMI());
        System.out.println("Optimal Weight: " + signedInUser.calculateOptimalWeight());
        System.out.println("Weight Status: " + signedInUser.determineWeightStatus());
        System.out.println("Weight to Lose or Gain: " + signedInUser.calculateWeightToLoseOrGain());
        System.out.println("Calories per Day: " + signedInUser.calculateCaloriesPerDay());
        System.out.println("determineWeightChangeRecommendation " + signedInUser.determineWeightChangeRecommendation());

    }


}
