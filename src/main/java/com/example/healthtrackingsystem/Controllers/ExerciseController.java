package com.example.healthtrackingsystem.Controllers;

import com.example.healthtrackingsystem.Interfaces.ExerciseDao;
import com.example.healthtrackingsystem.Models.Exercise;
import com.example.healthtrackingsystem.Models.Food;
import com.example.healthtrackingsystem.Models.UserExercise;
import com.example.healthtrackingsystem.Models.UserFood;
import com.example.healthtrackingsystem.dao.ExerciseDaoImpl;
import com.example.healthtrackingsystem.dao.FoodDaoImpl;
import com.example.healthtrackingsystem.dao.UserExerciseDaoImpl;
import com.example.healthtrackingsystem.dao.UserFoodDaoImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public class ExerciseController extends SceneController{
        @FXML
        private ComboBox<String> GYMTrainingComboBox;
        @FXML
        private ComboBox<String> GeneralActivityComboBox;
        private Timeline messageTimeline;
        @FXML
        private Text message;
        @FXML
        private DatePicker datePicker;
        private double totalBurnedCalories =0.00;
        @FXML
        private Label totalBurnedCaloriesText;
        @FXML
        private  TextField GYMTrainingTime;
        @FXML
        private  TextField generalActivityTime;


    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        updateGeneralActivityComboBox();
        updateGYMTrainingComboBox();


        messageTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1.5), this::hideMessage)
        );
        messageTimeline.setCycleCount(1);

    }

    private void updateComboBox(ComboBox<String> comboBox, List<Exercise> exercisesList) {
        comboBox.getItems().clear();
        for (Exercise exercise : exercisesList) {
            comboBox.getItems().add(exercise.getExerciseName());
        }
    }


    private List<Exercise> getGeneralActivities() {
        ExerciseDao exerciseDao= new ExerciseDaoImpl();
        return exerciseDao.findByExerciseType("General Activity");
    }
    private List<Exercise> getGYMTraining() {
        ExerciseDao exerciseDao= new ExerciseDaoImpl();
        return exerciseDao.findByExerciseType("GYM Training");
    }
    private void updateGYMTrainingComboBox() {
        List<Exercise> exercisesList = getGYMTraining();
        updateComboBox(GYMTrainingComboBox, exercisesList);
    }
    private void updateGeneralActivityComboBox() {
        List<Exercise> exercisesList = getGeneralActivities();
        updateComboBox(GeneralActivityComboBox, exercisesList);
    }



    private void handleExerciseSelection(String exerciseType, ComboBox<String> exerciseComboBox, TextField exerciseTextField) {
        String exerciseValue = exerciseComboBox.getValue();
        String exerciseInterval = exerciseTextField.getText();

        if (exerciseInterval == null || exerciseInterval.trim().isEmpty()) {
            showMessage("Please enter a valid training interval.");
            return;
        }
        if (exerciseValue ==null ){
            showMessage("Please Choose an exercise.");
            return;
        }
        int exerciseTimeIntervl = 0;
        try {
            exerciseTimeIntervl = Integer.parseInt(exerciseInterval);
            if (exerciseTimeIntervl <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid positive number for Training Interval.");
            return;
        }
        ExerciseDaoImpl exerciseDao = new ExerciseDaoImpl();
        Exercise selectedExercise = exerciseDao.findOneByExerciseName(exerciseValue);
        int userId = UserRepository.getCurrentUser().getUserId();
        int exerciseId = selectedExercise.getExerciseId();
        LocalDate foodDate = datePicker.getValue();
        Instant instant = foodDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);

        UserExercise userExercise=UserExercise.builder()
                .userId(userId)
                .exerciseId(exerciseId)
                .duration(exerciseTimeIntervl)
                .exerciseDate(utilDate)
                .build();

        UserExerciseDaoImpl userExerciseDao = new UserExerciseDaoImpl();
        userExerciseDao.save(userExercise);

        double calories =selectedExercise.getActivityTypeCalories();
        double burnedCalories = calories*exerciseTimeIntervl;
        totalBurnedCalories+=burnedCalories;
        totalBurnedCaloriesText.setText(String.valueOf(totalBurnedCalories));
        exerciseTextField.clear();
        showMessage("Added Successfully");

    }
    private void hideMessage(ActionEvent event) {
        message.setText("");
    }

    private void showMessage( String messageBody) {
        message.setText(messageBody);
        messageTimeline.playFromStart();
    }
    @FXML
    private void handleGeneralActivitySelection() {
        handleExerciseSelection("General Activity", GeneralActivityComboBox, generalActivityTime);
    }   @FXML
    private void handleGYMTrainingSelection() {
        handleExerciseSelection("GYM Training", GYMTrainingComboBox, GYMTrainingTime);
    }

}
