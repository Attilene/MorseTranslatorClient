package sample.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.models.json.JsonHistory;

public class HistoryPageController {
    private Stage dialStage;
    private JsonHistory[] histories;

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    public void setHistories(JsonHistory[] histories) { this.histories = histories; }

    @FXML
    private void handleDeleteHistory() {}

    @FXML
    private void handleCancel() { dialStage.close(); }
}
