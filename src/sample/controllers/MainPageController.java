package sample.controllers;

import javafx.fxml.FXML;
import sample.Main;
import sample.models.app.Person;

/**
 * Controller for managing mainPage.fxml form
 *
 * @author  Artem Bakanov aka Attilene
 */
public class MainPageController {
    /**
     * Instance of Main class created for showing child forms
     */
    private Main main;

    /**
     * Method for showing enterPage.fxml form
     */
    @FXML
    private void handleEnterAction() {
        Person person = main.showEnterPage();
        if (person.getLogin() != null) { main.showTranslatorPage(person); }
    }

    /**
     * Method for showing registrationPage.fxml form
     */
    @FXML
    private void handleRegistrationAction() { main.showRegistrationPage(); }

    /**
     * Setter for instance of Main class
     *
     * @param  main  instance of Main class
     */
    public void setMainApp(Main main) { this.main = main; }
}
