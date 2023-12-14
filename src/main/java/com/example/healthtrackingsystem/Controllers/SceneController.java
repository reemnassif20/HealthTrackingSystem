package com.example.healthtrackingsystem.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void SwitchToHome(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("/GUI files/Home.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToHistory(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("/GUI files/History.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToSettings(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("/GUI files/Settings.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToExercising(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("/GUI files/Exercising.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToMeals(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("/GUI files/Meals.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToAlarms(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("/GUI files/Alarms.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
