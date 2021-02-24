package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Person;
import sample.util.AlertsUtil;
import sample.util.DateUtil;

import java.time.format.DateTimeFormatter;

public class EditPersonPageController {
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

    private boolean delete = false;
    private Stage dialStage;
    private Person person;
    private Main main;

    public void setPerson(Person person) {
        this.person = person;
        firstNameField.setText(this.person.getFirstName());
        lastNameField.setText(this.person.getLastName());
        loginField.setText(this.person.getLogin());
        emailField.setText(this.person.getEmail());
        phoneNumberField.setText(this.person.getPhoneNumber());
        birthdayField.setValue(this.person.getBirthday());
        passwordField.setText(this.person.getPassword());
        repeatPasswordField.setText(this.person.getRepeatPassword());
    }

    public Person getPerson() { return person; }

    public boolean isDelete() { return delete; }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    @FXML
    private void handleUpdate () {
        if (isInputValid()) {
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

    @FXML
    private void handleDeleteProfile() {
        delete = AlertsUtil.showDeleteProfileConfirmationAlert(dialStage, main, person);
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
        if (phoneNumberField.getText() != null && phoneNumberField.getText().length() != 0) {
            try { Long.parseLong(phoneNumberField.getText()); }
            catch (NumberFormatException e) {
                errorMessage += "Неправильный формат номера телефона!\n";
            }
        }
        if (birthdayField.getValue() != null) {
            if (!DateUtil.validDate(birthdayField.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))) {
                errorMessage += "Неправильный формат даты рождения. Используйте формат dd.mm.yyyy!\n";
            }
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
