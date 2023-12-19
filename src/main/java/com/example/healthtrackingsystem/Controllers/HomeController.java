package com.example.healthtrackingsystem.Controllers;
import com.example.healthtrackingsystem.Models.User;
import com.example.healthtrackingsystem.dao.HistoryDaoImpl;
import com.example.healthtrackingsystem.dao.UserExerciseDaoImpl;
import com.example.healthtrackingsystem.dao.UserFoodDaoImpl;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


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
    @FXML
    private Text totalEatenCaloriesText;
    @FXML
    private Text totalBurnedCaloriesText;
    @FXML
    private Text totalEnteredCalories;


    private User signedInUser;

    @FXML
    public void initialize() {
        signedInUser = UserRepository.getCurrentUser();
        usernameText.setText(signedInUser.getUsername());


        HistoryDaoImpl historyDao = new HistoryDaoImpl();
        BigDecimal latestWeight= historyDao.findLatestHistoryByUserId(UserRepository.getCurrentUser().getUserId()).getHistoryWeight();
        UserRepository.getCurrentUser().setCurrentWeight(latestWeight);



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


        calculateAndDisplayTotalEatenCalories();
        calculateAndDisplayTotalBurnedCalories();
        calculateAndDisplayTotalEnteredCalories();
    }


    private double calculateAndDisplayTotalEatenCalories() {
        UserFoodDaoImpl userFoodDao =new UserFoodDaoImpl();

        int userId = UserRepository.getCurrentUser().getUserId();

        LocalDate foodDate = LocalDate.now();
        Instant instant = foodDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);

        double totalCalories= userFoodDao.calculateTotalCalories(userId,utilDate);
        totalEatenCaloriesText.setText(String.valueOf(totalCalories));
        return  totalCalories;
    }


    private double calculateAndDisplayTotalBurnedCalories() {
        UserExerciseDaoImpl userExerciseDao = new UserExerciseDaoImpl();

        int userId = UserRepository.getCurrentUser().getUserId();

        LocalDate exerciseDate = LocalDate.now();
        Instant instant = exerciseDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);
        double totalCalories= userExerciseDao.calculateTotalBurnedCalories(userId,utilDate);
        totalBurnedCaloriesText.setText(String.valueOf(totalCalories));
        return totalCalories;
    }
    private void calculateAndDisplayTotalEnteredCalories(){
        double enteringBodyCalories= calculateAndDisplayTotalEatenCalories()-calculateAndDisplayTotalBurnedCalories();
        totalEnteredCalories.setText(String.valueOf(enteringBodyCalories));

    }



}
