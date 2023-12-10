package com.example.healthtrackingsystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360, 240);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Health Tracking System");
        File image = new File("C:\\Users\\Reem\\Desktop\\HealthTrackingSystem\\src\\download.png");
        Image icon =new Image(image.toURI().toString());
        stage.getIcons().add(icon);
    }

    public static void main(String[] args) {
        launch();
    }

}