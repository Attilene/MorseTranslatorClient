package sample.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import sample.models.app.Person;
import sample.models.app.RegistrationEditModel;
import sample.models.json.Password;
import sample.models.json.User;
import sample.utils.AlertsUtil;
import sample.utils.ValidUtil;
import sample.utils.RequestsUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class EditPersonPageController extends RegistrationEditModel {
    private final Gson gson = new Gson();
    private boolean delete = false;
    private Person person;

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
                        put("password_hash", passwordField.getText());
                        put("salt", repeatPasswordField.getText());
                    }});
                    requestsUtil.thread.start();
                    RequestsUtil.runningThread(requestsUtil, dialStage);
                    if (!Objects.equals(requestsUtil.getResponse(), "") && requestsUtil.getResponse() != null) {
                        User user = gson.fromJson(requestsUtil.getResponse(), User.class);
                        Password password = user.getPassword();
                        person.setFirstName(user.getFirst_name());
                        person.setLastName(user.getLast_name());
                        person.setLogin(user.getLogin());
                        person.setEmail(user.getEmail());
                        person.setPhoneNumber(user.getPhone_number());
                        person.setBirthday(LocalDate.parse(user.getBirthday()));
                        person.setPassword(password.getHash());
                        person.setRepeatPassword(password.getSalt());
                        dialStage.close();
                    } else if (!requestsUtil.getDisconnect()
                            && Objects.equals(requestsUtil.getResponse(), ""))
                        AlertsUtil.showUserExistAlert(dialStage);
                }
            }
        }
    }

    @FXML
    private void handleDeleteProfile() {
        delete = AlertsUtil.showDeleteProfileConfirmationAlert(dialStage);
        if (delete) {
            RequestsUtil requestsUtil = new RequestsUtil("/user", "DELETE");
            requestsUtil.setParams(new HashMap<>() {{ put("id", person.getId().toString()); }});
            requestsUtil.thread.start();
            RequestsUtil.runningThread(requestsUtil, dialStage);
            if (Objects.equals(requestsUtil.getResponse(), "delete_success")) dialStage.close();
            else if (!requestsUtil.getDisconnect()
                    && Objects.equals(requestsUtil.getResponse(), "delete_failed"))
                AlertsUtil.showInternalServerErrorAlert(dialStage);
        }
    }

    public boolean isDelete() { return delete; }

    public Person getPerson() { return person; }

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
