package com.example.healthtrackingsystem.Controllers;


import com.example.healthtrackingsystem.Models.History;
import com.example.healthtrackingsystem.dao.HistoryDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HistoryController extends SceneController{



    @FXML
    private TableView<WeightHistoryRow> weightHistoryTable;

    @FXML
    private TableColumn<WeightHistoryRow, String> dateColumn;

    @FXML
    private TableColumn<WeightHistoryRow, String> weightColumn;


    private ObservableList<WeightHistoryRow> weightHistoryData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        initializeWeightHistoryTable();
        updateWeightHistoryTable();
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
}
