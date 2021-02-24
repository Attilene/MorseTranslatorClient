package sample.controller;

import javafx.fxml.FXML;
import sample.Main;

public class MainPageController {
    public Main main;

    @FXML
    private void handleEnterAction() {
        boolean okClicked = main.showEnterPage();
        if (okClicked) { System.out.println("Enter in profile"); } // TODO: Добавить запрос
    }

    @FXML
    private void handleRegistrationAction() {
        boolean okClicked = main.showRegistrationPage();
        if (okClicked) { System.out.println(main.getPersonData().get(0)); } // TODO: Добавить запрос
    }

    public void setMainApp(Main main) { this.main = main; }
}
