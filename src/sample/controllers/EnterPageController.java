package sample.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.models.app.EnterModel;
import sample.models.app.Person;
import sample.models.json.Password;
import sample.models.json.User;
import sample.models.to.dict.DictEnter;
import sample.utils.AlertsUtil;
import sample.utils.ValidUtil;
import sample.utils.RequestsUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class EnterPageController extends EnterModel {
    private final Gson gson = new Gson();
    private Stage dialStage;
    private Person person;

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
        if (ValidUtil.isInputValidEnter(this, dialStage)) {
            if (ValidUtil.isRegExValidEnter(this, dialStage)) {
                RequestsUtil requestsUtil = new RequestsUtil("/enter", "POST");
                requestsUtil.setParams(new DictEnter().setParams(new ArrayList<>() {{
                    add(userLogEmailField.getText());
                    add(passwordField.getText());
                    add(passwordField.getText());
                }}));
                requestsUtil.thread.start();
                RequestsUtil.runningThread(requestsUtil, dialStage);
                if (!Objects.equals(requestsUtil.getResponse(), "") && requestsUtil.getResponse() != null) {
                    User user = gson.fromJson(requestsUtil.getResponse(), User.class);
                    Password password = user.getPassword();
                    person.setId(user.getId());
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
                    AlertsUtil.showNoValidEnterAlert(dialStage);
            }
        }
    }

    @FXML
    private void handleCancel() { dialStage.close(); }
}
