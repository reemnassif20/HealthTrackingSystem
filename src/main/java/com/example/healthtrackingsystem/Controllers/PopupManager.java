package com.example.healthtrackingsystem.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PopupManager {

    private final Duration popupInterval;
    private final String popupMessage;
    private final Timeline popupTimeline;

    public PopupManager(Duration popupInterval, String popupMessage) {
        this.popupInterval = popupInterval;
        this.popupMessage = popupMessage;

        // Create a Timeline that triggers at the specified interval
        popupTimeline = new Timeline(
                new KeyFrame(popupInterval, event -> showPopup(null))
        );
        popupTimeline.setCycleCount(Timeline.INDEFINITE);
        popupTimeline.play();

        // Show the initial popup upon object creation
        showPopup(null);
    }

    public void showPopup(Stage ownerStage) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText(popupMessage);

            // Show the alert with the specified owner stage
            if (ownerStage != null) {
                alert.initOwner(ownerStage);
            }
            alert.showAndWait();
        });
    }
}
