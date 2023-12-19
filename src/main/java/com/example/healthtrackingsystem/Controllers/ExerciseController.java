package com.example.healthtrackingsystem.Controllers;

import com.example.healthtrackingsystem.Interfaces.ExerciseDao;
import com.example.healthtrackingsystem.Models.Exercise;
import com.example.healthtrackingsystem.Models.UserExercise;
import com.example.healthtrackingsystem.dao.ExerciseDaoImpl;
import com.example.healthtrackingsystem.dao.UserExerciseDaoImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ExerciseController extends SceneController {
    @FXML
    private ComboBox<String> GYMTrainingComboBox;
    @FXML
    private ComboBox<String> GeneralActivityComboBox;
    private Timeline messageTimeline;
    @FXML
    private Text message;
    @FXML
    private DatePicker datePicker;
    private double totalBurnedCalories = 0.00;
    @FXML
    private Label totalBurnedCaloriesText;
    @FXML
    private TextField GYMTrainingTime;
    @FXML
    private TextField generalActivityTime;
    @FXML
    private TableView<ExerciseTableRow> exerciseTable;
    @FXML
    private TableColumn<ExerciseTableRow, String> exerciseColumn;
    @FXML
    private TableColumn<ExerciseTableRow, String> intervalColumn;
    @FXML
    private TableColumn<ExerciseTableRow, String> caloriesConsumedColumn;
    @FXML
    private TableColumn<ExerciseTableRow, Button> deleteColumn;
    @FXML
    private ObservableList<ExerciseTableRow> exerciseData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        datePicker.setOnAction(this::handleDatePickerAction);

        updateGeneralActivityComboBox();
        updateGYMTrainingComboBox();

        messageTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1.5), this::hideMessage)
        );
        messageTimeline.setCycleCount(1);
        initializeExerciseTable();
        calculateAndDisplayTotalBurnedCalories();

    }

    private void updateComboBox(ComboBox<String> comboBox, List<Exercise> exercisesList) {
        comboBox.getItems().clear();
        for (Exercise exercise : exercisesList) {
            comboBox.getItems().add(exercise.getExerciseName());
        }
    }

    private List<Exercise> getGeneralActivities() {
        ExerciseDao exerciseDao = new ExerciseDaoImpl();
        return exerciseDao.findByExerciseType("General Activity");
    }

    private List<Exercise> getGYMTraining() {
        ExerciseDao exerciseDao = new ExerciseDaoImpl();
        return exerciseDao.findByExerciseType("GYM Training");
    }

    private void updateGYMTrainingComboBox() {
        List<Exercise> exercisesList = getGYMTraining();
        updateComboBox(GYMTrainingComboBox, exercisesList);
    }

    private void updateGeneralActivityComboBox() {
        List<Exercise> exercisesList = getGeneralActivities();
        updateComboBox(GeneralActivityComboBox, exercisesList);
    }

    private void handleExerciseSelection(ComboBox<String> exerciseComboBox, TextField exerciseTextField) {
        String exerciseValue = exerciseComboBox.getValue();
        String exerciseInterval = exerciseTextField.getText();

        if (exerciseInterval == null || exerciseInterval.trim().isEmpty()) {
            showMessage("Please enter a valid training interval.");
            return;
        }
        if (exerciseValue == null) {
            showMessage("Please Choose an exercise.");
            return;
        }
        int exerciseTimeIntervl = 0;
        try {
            exerciseTimeIntervl = Integer.parseInt(exerciseInterval);
            if (exerciseTimeIntervl <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid positive number for Training Interval.");
            return;
        }
        ExerciseDaoImpl exerciseDao = new ExerciseDaoImpl();
        Exercise selectedExercise = exerciseDao.findOneByExerciseName(exerciseValue);
        int userId = UserRepository.getCurrentUser().getUserId();
        int exerciseId = selectedExercise.getExerciseId();
        LocalDate foodDate = datePicker.getValue();
        Instant instant = foodDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);

        UserExercise userExercise = UserExercise.builder()
                .userId(userId)
                .exerciseId(exerciseId)
                .duration(exerciseTimeIntervl)
                .exerciseDate(utilDate)
                .build();

        UserExerciseDaoImpl userExerciseDao = new UserExerciseDaoImpl();
        userExerciseDao.save(userExercise);

        double calories = selectedExercise.getActivityTypeCalories();
        double burnedCalories = calories * exerciseTimeIntervl;
        totalBurnedCalories += burnedCalories;
        totalBurnedCaloriesText.setText(String.valueOf(totalBurnedCalories));
        exerciseTextField.clear();
        showMessage("Added Successfully");
        populateExerciseData();
    }

    private void hideMessage(ActionEvent event) {
        message.setText("");
    }

    private void showMessage(String messageBody) {
        message.setText(messageBody);
        messageTimeline.playFromStart();
    }

    @FXML
    private void handleGeneralActivitySelection() {
        handleExerciseSelection(GeneralActivityComboBox, generalActivityTime);
    }

    @FXML
    private void handleGYMTrainingSelection() {
        handleExerciseSelection(GYMTrainingComboBox, GYMTrainingTime);
    }

    private void initializeExerciseTable() {
        exerciseTable.getColumns().clear();

        exerciseColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseName"));
        intervalColumn.setCellValueFactory(new PropertyValueFactory<>("interval"));
        caloriesConsumedColumn.setCellValueFactory(new PropertyValueFactory<>("caloriesConsumed"));

        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    ExerciseTableRow rowData = getTableView().getItems().get(getIndex());
                    handleDeleteButtonClick(rowData.getUserExercise());
                });
            }

            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        exerciseTable.getColumns().addAll(exerciseColumn, intervalColumn, caloriesConsumedColumn, deleteColumn);

        // Populate table data
        populateExerciseData();

        // Set the populated data to the table
        exerciseTable.setItems(exerciseData);
    }


    private void populateExerciseData() {
        List<UserExercise> userExercises = getUserExercisesByUserIdAndDate();
        exerciseData.clear();

        for (UserExercise userExercise : userExercises) {
            Exercise exercise = getExerciseById(userExercise.getExerciseId());

            if (exercise != null) {
                ExerciseTableRow exerciseTableRow = new ExerciseTableRow(
                        exercise.getExerciseName(),
                        String.valueOf(userExercise.getDuration()),
                        String.valueOf(exercise.getActivityTypeCalories() * userExercise.getDuration()),
                        createDeleteButton(userExercise),
                        userExercise
                );

                exerciseData.add(exerciseTableRow);
            }
        }
    }


    private Exercise getExerciseById(int exerciseId) {
        ExerciseDaoImpl exerciseDao = new ExerciseDaoImpl();
        return exerciseDao.findById(exerciseId);
    }

    private List<UserExercise> getUserExercisesByUserIdAndDate() {
        UserExerciseDaoImpl userExerciseDao = new UserExerciseDaoImpl();

        int userId = UserRepository.getCurrentUser().getUserId();

        LocalDate foodDate = datePicker.getValue();
        Instant instant = foodDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);

        return userExerciseDao.findByUserIdAndDate(userId, utilDate);
    }

    private Button createDeleteButton(UserExercise userExercise) {
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> handleDeleteButtonClick(userExercise));
        return deleteButton;
    }


    private void handleDeleteButtonClick(UserExercise userExercise) {
        UserExerciseDaoImpl userExerciseDao = new UserExerciseDaoImpl();
        userExerciseDao.deleteByUserIdAndExerciseIdAndExerciseDate(
                userExercise.getUserId(),
                userExercise.getExerciseId(),
                userExercise.getExerciseDate()
        );

        populateExerciseData();
    }

    private void calculateAndDisplayTotalBurnedCalories() {
        List<UserExercise> userExercises = getUserExercisesByUserIdAndDate();

        double totalCalories = userExercises.stream()
                .mapToDouble(userExercise -> {
                    Exercise exercise = getExerciseById(userExercise.getExerciseId());
                    return exercise != null ? exercise.getActivityTypeCalories() * userExercise.getDuration() : 0.0;
                })
                .sum();

        totalBurnedCaloriesText.setText(String.valueOf(totalCalories));
    }


    public static class ExerciseTableRow {
        private final String exerciseName;
        private final String interval;
        private final String caloriesConsumed;
        private final Button deleteButton;
        private final UserExercise userExercise;

        public ExerciseTableRow(String exerciseName, String interval, String caloriesConsumed, Button deleteButton, UserExercise userExercise) {
            this.exerciseName = exerciseName;
            this.interval = interval;
            this.caloriesConsumed = caloriesConsumed;
            this.deleteButton = deleteButton;
            this.userExercise = userExercise;
        }

        public String getExerciseName() {
            return exerciseName;
        }

        public String getInterval() {
            return interval;
        }

        public String getCaloriesConsumed() {
            return caloriesConsumed;
        }

        public Button getDeleteButton() {
            return deleteButton;
        }

        public UserExercise getUserExercise() {
            return userExercise;
        }
    }
    private void handleDatePickerAction(ActionEvent event) {
       populateExerciseData();
       calculateAndDisplayTotalBurnedCalories();
    }

}

