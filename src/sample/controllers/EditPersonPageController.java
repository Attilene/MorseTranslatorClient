package sample.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.app.Person;
import sample.models.json.JsonPassword;
import sample.models.json.JsonUser;
import sample.models.to.dict.DictUpdateUser;
import sample.utils.AlertsUtil;
import sample.utils.ValidUtil;
import sample.utils.requests.DeleteRequestUtil;
import sample.utils.requests.PutRequestUtil;
import sample.utils.requests.RequestsUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
                    PutRequestUtil putRequestUtil = new PutRequestUtil("/user");
                    putRequestUtil.setParams(new DictUpdateUser().setParams(new ArrayList<>() {{
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
                    putRequestUtil.thread.start();
                    RequestsUtil.runningThread(putRequestUtil, dialStage);
                    if (!Objects.equals(putRequestUtil.getResponse(), "") && putRequestUtil.getResponse() != null) {
                        JsonUser jsonUser = gson.fromJson(putRequestUtil.getResponse(), JsonUser.class);
                        JsonPassword jsonPassword = jsonUser.getPassword();
                        person.setFirstName(jsonUser.getFirst_name());
                        person.setLastName(jsonUser.getLast_name());
                        person.setLogin(jsonUser.getLogin());
                        person.setEmail(jsonUser.getEmail());
                        person.setPhoneNumber(jsonUser.getPhone_number());
                        person.setBirthday(LocalDate.parse(jsonUser.getBirthday()));
                        person.setPassword(jsonPassword.getHash());
                        person.setRepeatPassword(jsonPassword.getSalt());
                        dialStage.close();
                    } else if (!putRequestUtil.getDisconnect()
                            && Objects.equals(putRequestUtil.getResponse(), ""))
                        AlertsUtil.showUserExistAlert(dialStage);
                }
            }
        }
    }

    @FXML
    private void handleDeleteProfile() {
        delete = AlertsUtil.showDeleteProfileConfirmationAlert(dialStage);
        if (delete) {
            DeleteRequestUtil deleteRequestUtil = new DeleteRequestUtil("/user");
            deleteRequestUtil.setParams(new HashMap<>() {{ put("id", person.getId().toString()); }});
            deleteRequestUtil.thread.start();
            RequestsUtil.runningThread(deleteRequestUtil, dialStage);
            if (Objects.equals(deleteRequestUtil.getResponse(), "delete_success")) dialStage.close();
            else if (!deleteRequestUtil.getDisconnect()
                    && Objects.equals(deleteRequestUtil.getResponse(), "delete_failed"))
                AlertsUtil.showUserExistAlert(dialStage);
        }
    }

    @FXML
    private void handleCancel() { dialStage.close(); }

    private boolean isInputValid( ) {
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
