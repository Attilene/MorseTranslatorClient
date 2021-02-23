package sample.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AboutPageController {
    private Stage dialStage;

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    @FXML
    public void handleBackAction() { dialStage.close(); }
}
