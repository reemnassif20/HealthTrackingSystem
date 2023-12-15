package com.example.healthtrackingsystem.Controllers;
import com.example.healthtrackingsystem.Models.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class HomeController extends SceneController{

    @FXML
    private Text usernameText;

    private User signedInUser;

    @FXML
    public void initialize() {
        signedInUser = UserRepository.getCurrentUser();
        usernameText.setText(signedInUser.getUsername());

    }
}
