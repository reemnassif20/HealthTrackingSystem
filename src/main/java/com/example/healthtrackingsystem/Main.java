package com.example.healthtrackingsystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/GUI files/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        String cssFile = getClass().getResource("/CSS files/styles.css").toExternalForm();
        scene.getStylesheets().add(cssFile);
        stage.setTitle("Health Tracking System");
        Image icon =new Image(getClass().getResourceAsStream("/Images/logo.jpeg"));
        stage.getIcons().add(icon);

    }

    public static void main(String[] args) {
        launch();
    }

}