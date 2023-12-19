package com.example.healthtrackingsystem.Controllers;
import com.example.healthtrackingsystem.Models.History;
import com.example.healthtrackingsystem.Models.User;
import com.example.healthtrackingsystem.dao.HistoryDaoImpl;
import com.example.healthtrackingsystem.dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class SignUpController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private MenuButton genderMenuButton;

    @FXML
    private TextField ageField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField weightField;

    @FXML
    private Button signUpButton;

    @FXML
    private Hyperlink signInButton;

    @FXML
    private Label signUpStatusLabel;
    private ToggleGroup genderToggleGroup;

    @FXML
    public void initialize() {
        if (genderMenuButton != null) {
            genderToggleGroup = new ToggleGroup();
            for (RadioMenuItem item : getGenderMenuItems()) {
                item.setToggleGroup(genderToggleGroup);
            }
        } else {
        }
    }

    private User createdAccount;

    @FXML
    private void handleSignUp(ActionEvent event) {
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String gender = getSelectedGender();

        try {
            int age = Integer.parseInt(ageField.getText());
            int height = Integer.parseInt(heightField.getText());
            BigDecimal weight = new BigDecimal(weightField.getText());

            if (password.equals(confirmPassword)) {
                User newUser = User.builder()
                        .username(email)
                        .password(password)
                        .fullName(fullName)
                        .email(email)
                        .gender(gender)
                        .age(age)
                        .height(height)
                        .currentWeight(weight)
                        .registrationDate(new Date())
                        .build();

                UserDaoImpl userDao = new UserDaoImpl();
                userDao.save(newUser);
                createdAccount=newUser;
                AccountCreatedSuccessfully(event);


            } else {
                signUpStatusLabel.setText("Passwords do not match. Please try again.");
            }
        } catch (NumberFormatException e) {
            signUpStatusLabel.setText("Invalid inputs. Please enter valid values.");
        }
        catch (IOException e){
            e.printStackTrace();  // Print the stack trace for debugging
            signUpStatusLabel.setText("Error reading input. Please try again.");

        }
    }


    @FXML
    public void SwitchToSignIn(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("/GUI files/SignIn.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void AccountCreatedSuccessfully(ActionEvent event) throws IOException {
        try {
            UserDaoImpl signedupUser =new UserDaoImpl();
            int signedupUserId =signedupUser.getUserByEmail(createdAccount.getEmail()).getUserId();
            BigDecimal signedupUserWeight =createdAccount.getCurrentWeight();

            History history = History.builder()
                    .historyWeight(signedupUserWeight)
                    .historyDate(new Date())
                    .userId(signedupUserId)
                    .build();

            // Save the history record
            HistoryDaoImpl historyDao = new HistoryDaoImpl();
            historyDao.save(history);



            root = FXMLLoader.load(getClass().getResource("/GUI files/AccountCreatedSuccessfully.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSelectedGender() {
        if (genderMenuButton == null) {
            return "Male";
        }

        RadioMenuItem selectedGenderItem = (RadioMenuItem) genderToggleGroup.getSelectedToggle();

        if (selectedGenderItem != null) {
            genderMenuButton.setText(selectedGenderItem.getText());
            return selectedGenderItem.getText();
        }

        return "Male";
    }

    private RadioMenuItem[] getGenderMenuItems() {
        return genderMenuButton.getItems().stream()
                .filter(item -> item instanceof RadioMenuItem)
                .toArray(RadioMenuItem[]::new);
    }


}
