package com.example.healthtrackingsystem;
import com.example.healthtrackingsystem.Controllers.PopupManager;
import com.example.healthtrackingsystem.dao.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/GUI files/SignIn.fxml"));
        Scene SignInScene = new Scene(fxmlLoader.load());
        stage.setScene(SignInScene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Health Tracking System");
        Image icon =new Image(getClass().getResourceAsStream("/Images/logo.jpeg"));
        stage.getIcons().add(icon);
        // Create an instance of PopupManager
        PopupManager popupManager = new PopupManager();

        // Show the pop-up with the current stage as the owner
        popupManager.showPopup(stage);



    }

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();
        if (con== null){
            System.out.println("Connection failed ");
        }
        else {
            System.out.println("Connection Success! ");
        }
        launch();


    }


}