package sample.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.models.app.RegistrationEditModel;
import sample.utils.AlertsUtil;
import sample.utils.ValidUtil;
import sample.utils.RequestsUtil;

import java.util.HashMap;
import java.util.Objects;

public class RegistrationPageController extends RegistrationEditModel {
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

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    @FXML
    private void handleRegistration () {
        if (ValidUtil.isInputValidRegistrationEdit(this, dialStage)) {
            if (ValidUtil.isInputValidLength(this, dialStage)) {
                if (ValidUtil.isRegExValidRegistrationEdit(this, dialStage)) {
                    RequestsUtil requestsUtil = new RequestsUtil("/registration", "POST");
                    requestsUtil.setParams(new HashMap<>() {{
                        put("first_name", firstNameField.getText());
                        put("last_name", lastNameField.getText());
                        put("login", loginField.getText());
                        put("email", emailField.getText());
                        put("phone_number", phoneNumberField.getText());
                        if (birthdayField.getValue() == null) put("birthday", null);
                        else put("birthday", birthdayField.getValue().toString());
                        put("password_hash", passwordField.getText());
                        put("salt", repeatPasswordField.getText());
                    }});
                    requestsUtil.thread.start();
                    RequestsUtil.runningThread(requestsUtil, dialStage);
                    if (Objects.equals(requestsUtil.getResponse(), "registration_success")) dialStage.close();
                    else if (!requestsUtil.getDisconnect()
                            && Objects.equals(requestsUtil.getResponse(), "registration_failed"))
                        AlertsUtil.showUserExistAlert(dialStage);
                }
            }
        }
    }

    @FXML
    private void handleCancel() { dialStage.close(); }
}
