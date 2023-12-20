package com.example.healthtrackingsystem.Controllers;
import com.example.healthtrackingsystem.Models.Reminder;
import com.example.healthtrackingsystem.Models.User;
import com.example.healthtrackingsystem.dao.ReminderDaoImpl;
import com.example.healthtrackingsystem.dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SignInController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label signInStatusLabel;


    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Sign in successful!");
            UserRepository.setCurrentUser(user);
            SwitchToHome(event);

            ReminderDaoImpl reminderDao = new ReminderDaoImpl();
            List<Reminder> userReminders = reminderDao.findAllByUserId(user.getUserId());

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

            for (Reminder reminder : userReminders) {
                String reminderMessage = reminder.getReminderMessage();
                int reminderPeriod = reminder.getReminderPeriod();

                Duration duration = Duration.minutes(reminderPeriod);

                // Schedule the creation of PopupManager with a delay
                executorService.schedule(() -> {
                    PopupManager popupManager = new PopupManager(duration, reminderMessage);
                    System.out.println(popupManager);
                }, reminderPeriod, TimeUnit.MINUTES);
            }


        } else {
            signInStatusLabel.setText("Incorrect email or password. Please try again.");
        }
    }

    @FXML
    public void switchToSignUp(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI files/SignUp.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void SwitchToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI files/Home.fxml"));
        root = loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
