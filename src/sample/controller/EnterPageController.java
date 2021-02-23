package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EnterPageController {
    @FXML
    private TextField userLogEmailField;
    @FXML
    private PasswordField passwordField;

    private Stage dialStage;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        userLogEmailField.setText(null);
        passwordField.setText(null);
    }

    public boolean isOkClicked() { return okClicked; }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    @FXML
    private void handleEnter() {

    }

    @FXML
    private void handleCancel() {
        dialStage.close();
    }
}
