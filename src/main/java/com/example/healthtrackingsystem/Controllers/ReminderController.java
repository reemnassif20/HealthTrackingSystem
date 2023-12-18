package com.example.healthtrackingsystem.Controllers;
import com.example.healthtrackingsystem.Models.Reminder;
import com.example.healthtrackingsystem.dao.ReminderDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ReminderController extends SceneController{

    @FXML
    private ComboBox<String> reminderPeriodComboBox;

    @FXML
    private TextField reminderMessageTextField;

    @FXML
    private Button setReminderButton;
    @FXML
    private Text message;

    @FXML
    private TableView<ReminderTableRow> reminderTable;

    @FXML
    private TableColumn<ReminderTableRow, String> reminderMessageColumn;

    @FXML
    private TableColumn<ReminderTableRow, String> reminderPeriodColumn;

    private ObservableList<ReminderTableRow> reminders = FXCollections.observableArrayList();



    private static final Map<String, Integer> PERIOD_TO_MINUTES = new HashMap<>();

    static {
        PERIOD_TO_MINUTES.put("30 min", 30);
        PERIOD_TO_MINUTES.put("1 hour", 60);
        PERIOD_TO_MINUTES.put("4 hours", 240);
        PERIOD_TO_MINUTES.put("6 hours", 360);
        PERIOD_TO_MINUTES.put("8 hours", 480);
        PERIOD_TO_MINUTES.put("12 hours", 720);
        PERIOD_TO_MINUTES.put("24 hours", 1440);
    }
    private static final Map<Integer, String> MINUTES_TO_PERIOD = new HashMap<>();

    static {
        MINUTES_TO_PERIOD.put(30, "30 min");
        MINUTES_TO_PERIOD.put(60, "1 hour");
        MINUTES_TO_PERIOD.put(240, "4 hours");
        MINUTES_TO_PERIOD.put(360, "6 hours");
        MINUTES_TO_PERIOD.put(480, "8 hours");
        MINUTES_TO_PERIOD.put(720, "12 hours");
        MINUTES_TO_PERIOD.put(1440, "24 hours");
    }

    private String convertMinutesToPeriod(int minutes) {
        return PERIOD_TO_MINUTES.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == minutes)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("Unknown");
    }


    @FXML
    public void initialize() {
        createAlarmsTable();
        intializeRemindersTable();
    }
    private void intializeRemindersTable(){
        ReminderDaoImpl reminderDao =new ReminderDaoImpl();
        reminders = reminderDao.findAllByUserId(UserRepository.getCurrentUser().getUserId())
                .stream()
                .map(reminder -> new ReminderTableRow(reminder.getReminderId(),reminder.getReminderMessage(), convertMinutesToPeriod(reminder.getReminderPeriod())))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        // Set the data into the table
        reminderTable.setItems(reminders);

    }
    private void createAlarmsTable(){
        // Initialize the table columns
        reminderMessageColumn.setCellValueFactory(new PropertyValueFactory<>("reminderMessage"));
        reminderPeriodColumn.setCellValueFactory(new PropertyValueFactory<>("reminderPeriod"));

        // Create the delete column
        TableColumn<ReminderTableRow, Void> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setPrefWidth(107.0);
        deleteColumn.setCellFactory(createDeleteButtonCellFactory());

        // Add all columns to the table
        reminderTable.getColumns().addAll(deleteColumn);

    }

    @FXML
     private void handleSetReminder(){
         String reminderMessage = reminderMessageTextField.getText();
         String selectedPeriod = reminderPeriodComboBox.getValue();


        if (reminderMessage == null || reminderMessage.trim().isEmpty()) {
            message.setText("Please enter a valid reminder message.");
            return;
        }

        if (selectedPeriod == null || selectedPeriod.trim().isEmpty()) {
            message.setText("Please select a valid reminder period.");
            return;
        }
        int reminderMinutes = PERIOD_TO_MINUTES.get(selectedPeriod);
        Reminder reminder= Reminder.builder()
                                   .userId(UserRepository.getCurrentUser().getUserId())
                                   .reminderMessage(reminderMessage)
                                   .reminderPeriod(reminderMinutes)
                                    .build();
        ReminderDaoImpl reminderDao =new ReminderDaoImpl();
        reminderDao.save(reminder);
        message.setText("AddedSuccessfully");
        reminderMessageTextField.clear();
        intializeRemindersTable();

     }


    public static class ReminderTableRow {
        private final String reminderMessage;
        private final String reminderPeriod;
        private final int reminderId;

        public ReminderTableRow(int reminderId,String reminderMessage, String reminderPeriod) {
            this.reminderId = reminderId;
            this.reminderMessage = reminderMessage;
            this.reminderPeriod = reminderPeriod;
        }

        public String getReminderMessage() {
            return reminderMessage;
        }

        public String getReminderPeriod() {
            return reminderPeriod;
        }

        public int getReminderId() {
            return reminderId;
        }

    }
    @FXML

    public void handleDeleteReminder(ReminderTableRow reminder) {
        try {
            ReminderDaoImpl reminderDao = new ReminderDaoImpl();
            reminderDao.deleteById(reminder.getReminderId());
            reminders.remove(reminder);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private Callback<TableColumn<ReminderTableRow, Void>, TableCell<ReminderTableRow, Void>> createDeleteButtonCellFactory() {
        return new Callback<TableColumn<ReminderTableRow, Void>, TableCell<ReminderTableRow, Void>>() {
            @Override
            public TableCell<ReminderTableRow, Void> call(TableColumn<ReminderTableRow, Void> param) {
                return new TableCell<>() {
                    private final Button deleteButton = new Button("Delete");

                    {

                        deleteButton.setOnAction(event -> {
                            ReminderTableRow reminder = getTableRow().getItem();
                            handleDeleteReminder(reminder);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
            }
        };
    }


}
