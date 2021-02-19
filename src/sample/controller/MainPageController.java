package sample.controller;

import javafx.fxml.FXML;
import sample.Main;

public class MainPageController {
    public Main main;

    @FXML
    private void initialize() {

    }

    public void setMainApp(Main main) {
        this.main = main;
    }
}
