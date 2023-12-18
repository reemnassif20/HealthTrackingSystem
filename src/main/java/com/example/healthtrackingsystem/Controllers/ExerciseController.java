package com.example.healthtrackingsystem.Controllers;

import com.example.healthtrackingsystem.Interfaces.ExerciseDao;
import com.example.healthtrackingsystem.Models.Exercise;
import com.example.healthtrackingsystem.dao.ExerciseDaoImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.List;

public class ExerciseController extends SceneController{
        @FXML
        private ComboBox<String> GYMTrainingComboBox;
        @FXML
        private ComboBox<String> GeneralActivityComboBox;


    @FXML
    public void initialize() {
//        datePicker.setValue(LocalDate.now());
//        datePicker.setOnAction(this::handleDatePickerAction);
updateGeneralActivityComboBox();
updateGYMTrainingComboBox();

//
//        successMessageTimeline = new Timeline(
//                new KeyFrame(Duration.seconds(0.5), this::hideSuccessMessage)
//        );
//        successMessageTimeline.setCycleCount(1);
//        totalCalories = 0;

    }

    private void updateComboBox(ComboBox<String> comboBox, List<Exercise> exercisesList) {
        comboBox.getItems().clear();
        for (Exercise exercise : exercisesList) {
            comboBox.getItems().add(exercise.getExerciseName());
        }
//        comboBox.getSelectionModel().selectFirst();
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



}
