package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.to.dict.DictRegistration;
import sample.utils.AlertsUtil;
import sample.utils.ValidUtil;
import sample.utils.requests.PostRequestUtil;
import sample.utils.requests.RequestsUtil;

import java.util.ArrayList;
import java.util.Objects;

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
    }

    public boolean isOkClicked() { return okClicked; }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    @FXML
    private void handleRegistration () {
        if (isInputValid()) {
            if (isInputValidLength()) {
                if (!ValidUtil.checkStandard(firstNameField.getText()))
                    AlertsUtil.showWrongFormatStandardAlert(dialStage, "Имя");
                else if (!ValidUtil.checkStandard(lastNameField.getText()))
                    AlertsUtil.showWrongFormatStandardAlert(dialStage, "Фамилия");
                else if (!ValidUtil.checkStandard(loginField.getText()))
                    AlertsUtil.showWrongFormatStandardAlert(dialStage, "Логин");
                else if (!ValidUtil.checkEmail(emailField.getText()))
                    AlertsUtil.showIncorrectEmailAlert(this.dialStage);
                else if (!ValidUtil.checkPassword(passwordField.getText()))
                    AlertsUtil.showWrongFormatPasswordAlert(this.dialStage);
                else if ((phoneNumberField.getText() != null && phoneNumberField.getText().length() != 0)
                        && !ValidUtil.checkPhoneNumber(phoneNumberField.getText())) {
                    AlertsUtil.showWrongFormatPhoneNumberAlert(this.dialStage);
                } else {
                    PostRequestUtil postRequestUtil = new PostRequestUtil("/registration");
                    postRequestUtil.setParams(new DictRegistration().setParams(new ArrayList<>() {{
                        add(firstNameField.getText());
                        add(lastNameField.getText());
                        add(loginField.getText());
                        add(emailField.getText());
                        add(phoneNumberField.getText());
                        if (birthdayField.getValue() == null) add(null);
                        else add(birthdayField.getValue().toString());
                        add(passwordField.getText());
                        add(repeatPasswordField.getText());
                    }}));
                    postRequestUtil.thread.start();
                    RequestsUtil.runningThread(postRequestUtil, dialStage);
                    if (Objects.equals(postRequestUtil.getResponse(), "registration_success")) dialStage.close();
                    else if (!postRequestUtil.getDisconnect()
                            && Objects.equals(postRequestUtil.getResponse(), "registration_failed"))
                        AlertsUtil.showUserExistAlert(dialStage);
                }
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

    private boolean isInputValidLength() {
        if (!ValidUtil.checkLength(firstNameField.getText(), 40)) {
            AlertsUtil.showBigStringAlert(dialStage, "Имя", 40);
            return false;
        }
        if (!ValidUtil.checkLength(lastNameField.getText(), 40)) {
            AlertsUtil.showBigStringAlert(dialStage, "Фамилия", 40);
            return false;
        }
        if (!ValidUtil.checkLength(loginField.getText(), 60)) {
            AlertsUtil.showBigStringAlert(dialStage, "Логин", 60);
            return false;
        }
        if (!ValidUtil.checkLength(emailField.getText(), 50)) {
            AlertsUtil.showBigStringAlert(dialStage, "Email", 50);
            return false;
        }
        return true;
    }
}
