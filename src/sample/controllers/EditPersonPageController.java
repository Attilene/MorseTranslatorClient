package sample.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import sample.models.app.Person;
import sample.models.app.fields.RegistrationEditModel;
import sample.models.json.User;
import sample.utils.alerts.AlertsUtil;
import sample.utils.validations.ValidUtil;
import sample.utils.requests.RequestsUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

/**
 * Controller for managing editPersonPage.fxml form
 *
 * @see     RegistrationEditModel
 * @author  Artem Bakanov aka Attilene
 */
public class EditPersonPageController extends RegistrationEditModel {
    /**
     * Google tool for converting data to json format and back
     */
    private final Gson gson = new Gson();

    /**
     * Flag which tracking the existence of user`s private cabinet
     */
    private boolean delete = false;

    /**
     * User`s personal data wrapped into instance of Person model class
     */
    private Person person;

    /**
     * Method for update button for changing user`s personal data
     */
    @FXML
    private void handleUpdate () {
        if (passwordToggle.isSelected()) {
            passwordField.setText(visiblePasswordField.getText());
            repeatPasswordField.setText(visibleRepeatPasswordField.getText());
        }
        if (ValidUtil.isInputValidRegistrationEdit(this, dialStage)) {
            if (ValidUtil.isInputValidLength(this, dialStage)) {
                if (ValidUtil.isRegExValidRegistrationEdit(this, dialStage)) {
                    RequestsUtil requestsUtil = new RequestsUtil("/user", "PUT");
                    requestsUtil.setParams(new HashMap<>() {{
                        put("id", person.getId().toString());
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
                    if (!Objects.equals(requestsUtil.getResponse(), "") && requestsUtil.getResponse() != null) {
                        User user = gson.fromJson(requestsUtil.getResponse(), User.class);
                        person.setFirstName(user.getFirst_name());
                        person.setLastName(user.getLast_name());
                        person.setLogin(user.getLogin());
                        person.setEmail(user.getEmail());
                        person.setPhoneNumber(user.getPhone_number());
                        if (user.getBirthday() != null)
                            person.setBirthday(LocalDate.parse(user.getBirthday()));
                        person.setPassword(passwordField.getText());
                        person.setRepeatPassword(passwordField.getText());
                        dialStage.close();
                    } else if (!requestsUtil.getDisconnect()
                            && Objects.equals(requestsUtil.getResponse(), ""))
                        AlertsUtil.showUserExistAlert(dialStage);
                }
            }
        }
    }

    /**
     * Method for delete button for deleting user`s private cabinet
     */
    @FXML
    private void handleDeleteProfile() {
        delete = AlertsUtil.showDeleteProfileConfirmationAlert(dialStage);
        if (delete) {
            RequestsUtil requestsUtil = new RequestsUtil("/user", "DELETE");
            requestsUtil.setParams(new HashMap<>() {{
                put("id", person.getId().toString());
                put("password", person.getPassword());
            }});
            requestsUtil.thread.start();
            RequestsUtil.runningThread(requestsUtil, dialStage);
            if (Objects.equals(requestsUtil.getResponse(), "delete_success")) dialStage.close();
            else if (!requestsUtil.getDisconnect()
                    && Objects.equals(requestsUtil.getResponse(), "delete_failed"))
                AlertsUtil.showInternalServerErrorAlert(dialStage);
        }
    }

    /**
     * Getter for current condition of user`s private cabinet
     *
     * @return  tracked deleting flag
     */
    public boolean isDelete() { return delete; }

    /**
     * Getter for user`s personal data
     *
     * @return  instance of Person model contained user`s data
     */
    public Person getPerson() { return person; }

    /**
     * Setter for user`s personal data
     *
     * @param  person  user`s personal data wrapped into instance of Person model class
     */
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
}
