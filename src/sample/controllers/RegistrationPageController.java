package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Person;
import sample.utils.AlertsUtil;
import sample.utils.ValidUtil;

public class RegistrationPageController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private DatePicker birthdayField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField repeatPasswordField;

    private boolean okClicked = false;
    private Stage dialStage;
    private Person person;
    private Main main;

    @FXML
    public void initialize() {
        firstNameField.setText(null);
        lastNameField.setText(null);
        loginField.setText(null);
        emailField.setText(null);
        phoneNumberField.setText(null);
        birthdayField.setValue(null);
        passwordField.setText(null);
        repeatPasswordField.setText(null);
        person = new Person();
    }

    public boolean isOkClicked() { return okClicked; }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    @FXML
    private void handleRegistration () {
        if (isInputValid()) {
            if (!ValidUtil.checkEmail(emailField.getText()))
                AlertsUtil.showIncorrectEmailAlert(this.dialStage);
            else if (!ValidUtil.checkPassword(passwordField.getText()))
                AlertsUtil.showWrongFormatPasswordAlert(this.dialStage);
            else if ((phoneNumberField.getText() != null && phoneNumberField.getText().length() != 0)
                    && !ValidUtil.checkPhoneNumber(phoneNumberField.getText())) {
                AlertsUtil.showWrongFormatPhoneNumberAlert(this.dialStage);
            } else {
                // TODO: Веб-запрос на обновление данных пользователя
                main.getPersonData().remove(person);
                person.setFirstName(firstNameField.getText());
                person.setLastName(lastNameField.getText());
                person.setLogin(loginField.getText());
                person.setEmail(emailField.getText());
                person.setPhoneNumber(phoneNumberField.getText());
                person.setBirthday(birthdayField.getValue());
                person.setPassword(passwordField.getText());
                person.setRepeatPassword(repeatPasswordField.getText());
                main.getPersonData().add(person);
                dialStage.close();
            }
        }
    }

    @FXML
    private void handleCancel() { dialStage.close(); }

    private boolean isInputValid() {
        String errorMessage = "";
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Нет имени!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Нет фамилии!\n";
        }
        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "Нет логина!\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Нет почты!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Нет пароля!\n";
        }
        if (repeatPasswordField.getText() == null || repeatPasswordField.getText().length() == 0) {
            errorMessage += "Нет повтора пароля!\n";
        }
        if ((passwordField.getText() != null && repeatPasswordField.getText() != null) &&
                !passwordField.getText().equals(repeatPasswordField.getText())) {
            errorMessage += "Повтор пароля не совпадает с паролем!\n";
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
