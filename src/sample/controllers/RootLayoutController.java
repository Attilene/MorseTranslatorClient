package sample.controllers;

import javafx.fxml.FXML;
import sample.Main;

public class RootLayoutController {
    private Main main;

    @FXML
    public void handleOpenAbout() { main.showAboutPage(); }

    public void setMain(Main main) { this.main = main; }
}
