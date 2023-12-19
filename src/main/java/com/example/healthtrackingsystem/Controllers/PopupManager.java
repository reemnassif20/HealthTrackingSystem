package com.example.healthtrackingsystem.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PopupManager {

    private static final Duration POPUP_INTERVAL = Duration.minutes(.25);

    private final Timeline popupTimeline;

    public PopupManager() {
        // Create a Timeline that triggers every 30 minutes
        popupTimeline = new Timeline(
                new KeyFrame(POPUP_INTERVAL, event -> showPopup(null))
        );
        popupTimeline.setCycleCount(Timeline.INDEFINITE);
        popupTimeline.play();
    }

    public void showPopup(Stage ownerStage) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Time to take a break!");

            // Show the alert with the specified owner stage
            if (ownerStage != null) {
                alert.initOwner(ownerStage);
            }
            alert.showAndWait();
        });
    }

}