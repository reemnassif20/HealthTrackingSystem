package com.example.healthtrackingsystem.Controllers;

import com.example.healthtrackingsystem.Interfaces.UserDao;
import com.example.healthtrackingsystem.Models.History;
import com.example.healthtrackingsystem.Models.User;
import com.example.healthtrackingsystem.dao.HistoryDaoImpl;
import com.example.healthtrackingsystem.dao.UserDaoImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SettingsController extends SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField newUsernameField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Text settingAlertText;
    @FXML
    private Text measurementsAlertText;
    @FXML
    private TextField newWeightField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Text successMessage;
    private Timeline successMessageTimeline;
    private UserDao userDao = new UserDaoImpl();
    @FXML
    public void initialize(){
        datePicker.setValue(LocalDate.now());
        datePicker.setOnAction(this::handleDatePickerAction);
        successMessageTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), this::hideSuccessMessage)
        );
        successMessageTimeline.setCycleCount(1);

    }

    @FXML
    private void handleUpdateUserName() {
        int userId = UserRepository.getCurrentUser().getUserId();
        String newUsername = newUsernameField.getText();


        // Check for null or empty values
        if (newUsername == null || newUsername.trim().isEmpty()) {
            settingAlertText.setText("user name cannot be empty!");

            return;
        }

        userDao.updateUsername(userId, newUsername);
        newUsernameField.clear();
        settingAlertText.setText("user name updated successfully!");
        UserRepository.getCurrentUser().setUsername(newUsername);
    }

    @FXML
    public void handleUpdatePassword(ActionEvent event) throws IOException {
        int userId = UserRepository.getCurrentUser().getUserId();

        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Check if password is empty
        if (newPassword.isEmpty()) {
            settingAlertText.setText("Password can't be empty!");
            return;
        }

        // Check if password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            settingAlertText.setText("Password and Confirm Password do not match!");
            return;
        }

        // Update password
        userDao.updatePassword(userId, newPassword);
        settingAlertText.setText("Password updated successfully!");

        newPasswordField.clear();
        confirmPasswordField.clear();
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI files/PasswordChangedSuccessfully.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleDatePickerAction(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();
        System.out.println("Selected date: " + selectedDate);
    }
    public void handleUpdateWeight() {
        LocalDate weightDate = datePicker.getValue();
        Instant instant = weightDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);

        String dateWeight = newWeightField.getText().trim();
        if (dateWeight.isEmpty()) {
            successMessage.setText("Please enter a weight value.");
            return;
        }

        try {
            BigDecimal weightValue = new BigDecimal(dateWeight);

            if (weightValue.compareTo(BigDecimal.ZERO) <= 0) {
                successMessage.setText("Please enter a positive non-zero weight value.");
                return;
            }

            History history = History.builder()
                    .historyWeight(weightValue)
                    .historyDate(utilDate)
                    .userId(UserRepository.getCurrentUser().getUserId())
                    .build();

            HistoryDaoImpl historyDao = new HistoryDaoImpl();
            historyDao.save(history);
            showSuccessMessage();
            newWeightField.clear();
        } catch (NumberFormatException e) {
            successMessage.setText("Please enter a valid weight value.");
        }
    }

    private void hideSuccessMessage(ActionEvent event) {
        successMessage.setText("");
    }

    private void showSuccessMessage() {
        successMessage.setText("Added Successfully!");
        successMessageTimeline.playFromStart();
    }
}
