package com.example.healthtrackingsystem.Controllers;

import com.example.healthtrackingsystem.Interfaces.UserDao;
import com.example.healthtrackingsystem.Models.User;
import com.example.healthtrackingsystem.dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

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
    private UserDao userDao = new UserDaoImpl();

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


}
