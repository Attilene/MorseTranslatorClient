package sample.controller;

import javafx.fxml.FXML;
import sample.Main;
import sample.model.Person;

public class MainPageController {
    public Main main;

    @FXML
    private void handleEnterAction() {
        Person person = main.showEnterPage();
        if (person.getLogin() != null) { main.showTranslatorPage(person); } // TODO: Добавить запрос
    }

    @FXML
    private void handleRegistrationAction() {
        boolean okClicked = main.showRegistrationPage();
        if (okClicked) { System.out.println(main.getPersonData().get(0)); } // TODO: Добавить запрос
    }

    public void setMainApp(Main main) { this.main = main; }
}
