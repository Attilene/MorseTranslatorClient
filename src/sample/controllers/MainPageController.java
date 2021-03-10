package sample.controllers;

import javafx.fxml.FXML;
import sample.Main;
import sample.models.app.Person;

public class MainPageController {
    public Main main;

    @FXML
    private void handleEnterAction() {
        Person person = main.showEnterPage();
        if (person.getLogin() != null) { main.showTranslatorPage(person); }
    }

    @FXML
    private void handleRegistrationAction() { main.showRegistrationPage(); }

    public void setMainApp(Main main) { this.main = main; }
}
