package sample.controllers;

import javafx.fxml.FXML;
import sample.models.app.fields.RegistrationEditModel;
import sample.utils.alerts.AlertsUtil;
import sample.utils.validations.ValidUtil;
import sample.utils.requests.RequestsUtil;

import java.util.HashMap;
import java.util.Objects;

/**
 * Controller for managing registrationPage.fxml form
 *
 * @see     RegistrationEditModel
 * @author  Artem Bakanov aka Attilene
 */
public class RegistrationPageController extends RegistrationEditModel {
    /**
     * The initializing method of the controller, which is started
     * at creation of instance of RegistrationPageController class
     */
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

    /**
     * Method for registration button for sign up and creating user`s private cabinet
     */
    @FXML
    private void handleRegistration () {
        if (passwordToggle.isSelected()) {
            passwordField.setText(visiblePasswordField.getText());
            repeatPasswordField.setText(visibleRepeatPasswordField.getText());
        }
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
                        put("password", passwordField.getText());
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
}
