package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.models.json.History;
import sample.utils.AlertsUtil;
import sample.utils.RequestsUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class HistoryPageController {
    @FXML
    private TableView<History> historyTableView;
    @FXML
    private TableColumn<History, String> startStringColumn;
    @FXML
    private TableColumn<History, String> endStringColumn;
    @FXML
    private TableColumn<History, Date> operationTimeColumn;

    private Stage dialStage;
    private ObservableList<History> historiesData;

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    public void setHistories(History[] histories) {
        historiesData = FXCollections.observableArrayList();
        historiesData.addAll(Arrays.asList(histories));
    }

    public ObservableList<History> getHistories() { return historiesData; }

    public void showHistoryContent() {
        startStringColumn.setCellValueFactory(new PropertyValueFactory<>("start_string"));
        endStringColumn.setCellValueFactory(new PropertyValueFactory<>("end_string"));
        operationTimeColumn.setCellValueFactory(new PropertyValueFactory<>("operation_time"));
        historyTableView.setItems(historiesData);
    }

    @FXML
    private void handleDeleteHistory() {
        int selectedIndex = historyTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            RequestsUtil requestsUtil = new RequestsUtil("/history", "DELETE");
            requestsUtil.setParams(new HashMap<>() {{
                put("id", historyTableView.getItems().get(selectedIndex).getId().toString());
            }});
            requestsUtil.thread.start();
            RequestsUtil.runningThread(requestsUtil, dialStage);
            if (Objects.equals(requestsUtil.getResponse(), "history_delete_success"))
                historyTableView.getItems().remove(selectedIndex);
            else if (!requestsUtil.getDisconnect()
                    && Objects.equals(requestsUtil.getResponse(), "history_delete_failed"))
                AlertsUtil.showInternalServerErrorAlert(dialStage);
        }
        else { AlertsUtil.showNotSelectedHistory(dialStage); }
    }

    @FXML
    public void handleSaveTXT() {}

    @FXML
    public void handleSaveCSV() {}

    @FXML
    private void handleCancel() { dialStage.close(); }
}
