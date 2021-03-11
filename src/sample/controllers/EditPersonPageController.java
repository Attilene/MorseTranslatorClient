package sample.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.models.app.Person;
import sample.models.app.RegistrationEditModel;
import sample.models.json.Password;
import sample.models.json.User;
import sample.models.to.dict.DictUpdateUser;
import sample.utils.AlertsUtil;
import sample.utils.ValidUtil;
import sample.utils.RequestsUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EditPersonPageController extends RegistrationEditModel {
    private final Gson gson = new Gson();
    private boolean delete = false;
    private Stage dialStage;
    private Person person;

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
        if (ValidUtil.isInputValidRegistrationEdit(this, dialStage)) {
            if (ValidUtil.isInputValidLength(this, dialStage)) {
                if (ValidUtil.isRegExValidRegistrationEdit(this, dialStage)) {
                    RequestsUtil requestsUtil = new RequestsUtil("/user", "PUT");
                    requestsUtil.setParams(new DictUpdateUser().setParams(new ArrayList<>() {{
                        add(person.getId().toString());
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

    @FXML
    private void handleCancel() { dialStage.close(); }
}
