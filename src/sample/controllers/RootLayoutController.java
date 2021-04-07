package sample.controllers;

import javafx.fxml.FXML;
import sample.Main;

/**
 * Controller for managing rootLayout.fxml form
 *
 * @author  Artem Bakanov aka Attilene
 */
public class RootLayoutController {
    /**
     * Instance of Main class created for showing child forms
     */
    private Main main;

    /**
     * Method for about button in menu bar for opening page contained information about author and application
     */
    @FXML
    public void handleOpenAbout() { main.showAboutPage(); }

    /**
     * Setter for instance of Main class
     *
     * @param  main  instance of Main class
     */
    public void setMain(Main main) { this.main = main; }
}
