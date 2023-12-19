package com.example.healthtrackingsystem.Controllers;


import com.example.healthtrackingsystem.Models.*;
import com.example.healthtrackingsystem.dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class HistoryController extends SceneController{



    @FXML
    private TableView<WeightHistoryRow> weightHistoryTable;

    @FXML
    private TableColumn<WeightHistoryRow, String> dateColumn;

    @FXML
    private TableColumn<WeightHistoryRow, String> weightColumn;


    @FXML
    private TableView<FoodRow> foodEntryTable;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Text totalEatenCaloriesText;

    @FXML
    private TableColumn<FoodRow, String> foodNameColumn;

    @FXML
    private TableColumn<FoodRow, String> foodQuantityCaloriesColumn;

    @FXML
    private TableColumn<FoodRow, String> foodMealColumn;

    @FXML
    private TableColumn<FoodRow, Button> deleteFoodColumn;

    private ObservableList<FoodRow> foodDataList = FXCollections.observableArrayList();


    private ObservableList<WeightHistoryRow> weightHistoryData = FXCollections.observableArrayList();


    @FXML
    private TableView<ExerciseController.ExerciseTableRow> exerciseTable;
    @FXML
    private TableColumn<ExerciseController.ExerciseTableRow, String> exerciseColumn;
    @FXML
    private TableColumn<ExerciseController.ExerciseTableRow, String> intervalColumn;
    @FXML
    private TableColumn<ExerciseController.ExerciseTableRow, String> caloriesConsumedColumn;
    @FXML
    private TableColumn<ExerciseController.ExerciseTableRow, Button> deleteColumn;
    @FXML
    private ObservableList<ExerciseController.ExerciseTableRow> exerciseData = FXCollections.observableArrayList();
    @FXML
    private Text totalBurnedCaloriesText;


    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        datePicker.setOnAction(this::handleDatePickerAction);
        initializeWeightHistoryTable();
        updateWeightHistoryTable();
        initializeFoodTable();
        updateFoodTable();
        calculateAndDisplayTotalEatenCalories();
        initializeExerciseTable();
        calculateAndDisplayTotalBurnedCalories();
    }
    private void handleDatePickerAction(ActionEvent event) {
        updateFoodTable();
        calculateAndDisplayTotalEatenCalories();
        populateExerciseData();
        calculateAndDisplayTotalBurnedCalories();
    }

    private void initializeWeightHistoryTable() {
        weightHistoryTable.getColumns().clear();
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        weightHistoryTable.getColumns().addAll(dateColumn, weightColumn);
    }

    private void updateWeightHistoryTable() {
        HistoryDaoImpl historyDao = new HistoryDaoImpl();
        int userId = UserRepository.getCurrentUser().getUserId();
        List<History> historyList = historyDao.findAllByUserId(userId);


        weightHistoryData.clear();
        for (History history : historyList) {
            WeightHistoryRow row = new WeightHistoryRow(
                    history.getHistoryDate().toString(),
                    String.valueOf(history.getHistoryWeight())

            );
            weightHistoryData.add(row);
        }

        weightHistoryTable.setItems(weightHistoryData);
    }




    public static class WeightHistoryRow {
        private final String date;
        private final String weight;

        public WeightHistoryRow(String date, String weight) {
            this.date = date;
            this.weight = weight;

        }

        public String getDate() {
            return date;
        }

        public String getWeight() {
            return weight;
        }

    }

    private void initializeFoodTable() {
        foodEntryTable.getColumns().clear();
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        foodQuantityCaloriesColumn.setCellValueFactory(new PropertyValueFactory<>("foodQuantity"));
        foodMealColumn.setCellValueFactory(new PropertyValueFactory<>("foodMeal"));
        deleteFoodColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        deleteFoodColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    FoodRow rowData = getTableView().getItems().get(getIndex());
                    handleDeleteFoodButton(rowData);
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

        foodEntryTable.getColumns().addAll(foodNameColumn, foodQuantityCaloriesColumn, foodMealColumn, deleteFoodColumn);
    }

    private List<UserFood> getUserFoodByUserIdAndDate() {
        UserFoodDaoImpl userFoodDao = new UserFoodDaoImpl();

        int userId = UserRepository.getCurrentUser().getUserId();

        LocalDate foodDate = datePicker.getValue();
        Instant instant = foodDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);

        return userFoodDao.findByUserIdAndDate(userId,utilDate);
    }



    private void updateFoodTable() {

        List<UserFood> foodList = getUserFoodByUserIdAndDate();
        foodDataList.clear();
        for (UserFood userFood : foodList) {
            Food food = getFoodByID(userFood.getFoodId());
            Button deleteButton = new Button("Delete");
            if (food != null) {
                FoodRow foodRow = new FoodRow(
                        food.getFoodName(),
                        String.valueOf((food.getCaloriesPerHundredUnits()*userFood.getQuantity())/100),
                        userFood.getMealType(),
                        deleteButton,
                        food.getFoodId()
                );
                foodDataList.add(foodRow);
            }

        }

        foodEntryTable.setItems(foodDataList);
    }


    private  Food getFoodByID(int foodId){
        FoodDaoImpl foodDao = new FoodDaoImpl();
        return foodDao.findById(foodId);
    }
    private void handleDeleteFoodButton(FoodRow foodRow) {

        int userId = UserRepository.getCurrentUser().getUserId();

        LocalDate foodDate = datePicker.getValue();
        Instant instant = foodDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);

        UserFoodDaoImpl userFoodDao= new UserFoodDaoImpl();
        userFoodDao.deleteByUserIdAndFoodIdAndFoodDate(userId, foodRow.getFoodId(), utilDate);

        foodDataList.remove(foodRow);

    }
    private void calculateAndDisplayTotalEatenCalories() {
        UserFoodDaoImpl userFoodDao =new UserFoodDaoImpl();

        int userId = UserRepository.getCurrentUser().getUserId();

        LocalDate foodDate = datePicker.getValue();
        Instant instant = foodDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);

        double totalCalories= userFoodDao.calculateTotalCalories(userId,utilDate);
        totalEatenCaloriesText.setText(String.valueOf(totalCalories));
    }

    public static class FoodRow {
        private final String foodName;
        private final String foodQuantity;
        private final String foodMeal;
        private final Button deleteButton;
        private final int foodId;

        public FoodRow(String foodName, String foodQuantity, String foodMeal, Button deleteButton,int foodId) {
            this.foodName = foodName;
            this.foodQuantity = foodQuantity;
            this.foodMeal = foodMeal;
            this.deleteButton = deleteButton;
            this.foodId=foodId;
        }

        public int getFoodId() {
            return foodId;
        }

        public String getFoodName() {
            return foodName;
        }

        public String getFoodQuantity() {
            return foodQuantity;
        }

        public String getFoodMeal() {
            return foodMeal;
        }

        public Button getDeleteButton() {
            return deleteButton;
        }
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
                    ExerciseController.ExerciseTableRow rowData = getTableView().getItems().get(getIndex());
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
                ExerciseController.ExerciseTableRow exerciseTableRow = new ExerciseController.ExerciseTableRow(
                        exercise.getExerciseName(),
                        String.valueOf(userExercise.getDuration()),
                        String.valueOf(exercise.getActivityTypeCalories() * userExercise.getDuration()),
                        createDeleteButton(userExercise),
                        userExercise
                );

                exerciseData.add(exerciseTableRow);
                calculateAndDisplayTotalBurnedCalories();
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
        calculateAndDisplayTotalBurnedCalories();

    }

    private void calculateAndDisplayTotalBurnedCalories() {
        UserExerciseDaoImpl userExerciseDao = new UserExerciseDaoImpl();

        int userId = UserRepository.getCurrentUser().getUserId();

        LocalDate exerciseDate = datePicker.getValue();
        Instant instant = exerciseDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date utilDate = Date.from(instant);
        double totalCalories= userExerciseDao.calculateTotalBurnedCalories(userId,utilDate);
        totalBurnedCaloriesText.setText(String.valueOf(totalCalories));
    }





}
