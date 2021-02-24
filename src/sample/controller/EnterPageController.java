package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Person;
import sample.util.AlertsUtil;

public class EnterPageController {
    @FXML
    private TextField userLogEmailField;
    @FXML
    private PasswordField passwordField;

    private Stage dialStage;
    private boolean okClicked = false;
    private Person person;
    private Main main;

    @FXML
    private void initialize() {
        userLogEmailField.setText(null);
        passwordField.setText(null);
        person = new Person();
    }

    public boolean isOkClicked() { return okClicked; }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    @FXML
    private void handleEnter() {
        if (isInputValid()) {
            // TODO: Веб-запрос на получение пользователя
            if (!main.getPersonData().isEmpty()) {
                person = main.getPersonData().get(0);
                if (person.getLogin().equals(userLogEmailField.getText()) ||
                        person.getEmail().equals(userLogEmailField.getText())) {
                    okClicked = true;
                    dialStage.close();
                } else {
                    AlertsUtil.showNoValidEnterAlert(dialStage);
                }
            }
            else AlertsUtil.showNoValidEnterAlert(dialStage);
        }
    }

    @FXML
    private void handleCancel() { dialStage.close(); }

    private boolean isInputValid() {
        String errorMessage = "";
        if (userLogEmailField.getText() == null || userLogEmailField.getText().length() == 0) {
            errorMessage += "Нет логина/почты!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Нет пароля!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            AlertsUtil.showInputValidAlert(dialStage, errorMessage);
            return false;
        }
    }

    public void setMain(Main main) { this.main = main; }
}
