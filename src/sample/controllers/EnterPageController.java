package sample.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.app.Person;
import sample.models.json.JsonPassword;
import sample.models.json.JsonUser;
import sample.models.to.dict.DictEnter;
import sample.utils.AlertsUtil;
import sample.utils.ValidUtil;
import sample.utils.requests.PostRequestUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class EnterPageController {
    @FXML
    private TextField userLogEmailField;
    @FXML
    private PasswordField passwordField;

    private final Gson gson = new Gson();
    private Stage dialStage;
    private Person person;
    private JsonUser jsonUser;
    private JsonPassword jsonPassword;

    @FXML
    private void initialize() {
        userLogEmailField.setText(null);
        passwordField.setText(null);
        person = new Person();
    }

    public Person getPerson() { return person; }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    @FXML
    private void handleEnter() {
        if (isInputValid()) {
            if (ValidUtil.checkStandard(userLogEmailField.getText())
                    || ValidUtil.checkEmail(userLogEmailField.getText())) {
                PostRequestUtil postRequestUtil = new PostRequestUtil("/enter");
                postRequestUtil.setParams(new DictEnter().setParams(new ArrayList<>() {{
                    add(userLogEmailField.getText());
                    add(passwordField.getText());
                    add(passwordField.getText());
                }}));
                postRequestUtil.thread.start();
                while (!postRequestUtil.thread.isInterrupted()) {
                    if (postRequestUtil.getDisconnect()) {
                        AlertsUtil.showInternalServerErrorAlert(dialStage);
                        postRequestUtil.setDisconnect(false);
                        break;
                    }
                    if (postRequestUtil.getResponse() != null) break;
                }
                if (!Objects.equals(postRequestUtil.getResponse(), "")) {
                    jsonUser = gson.fromJson(postRequestUtil.getResponse(), JsonUser.class);
                    jsonPassword = jsonUser.getPassword();
                    person.setFirstName(jsonUser.getFirst_name());
                    person.setLastName(jsonUser.getLast_name());
                    person.setLogin(jsonUser.getLogin());
                    person.setEmail(jsonUser.getEmail());
                    person.setPhoneNumber(jsonUser.getPhone_number());
                    person.setBirthday(LocalDate.parse(jsonUser.getBirthday()));
                    person.setPassword(jsonPassword.getHash());
                    person.setRepeatPassword(jsonPassword.getSalt());
                    dialStage.close();
                } else if (!postRequestUtil.getDisconnect()
                        && Objects.equals(postRequestUtil.getResponse(), ""))
                    AlertsUtil.showNoValidEnterAlert(dialStage);
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
}
