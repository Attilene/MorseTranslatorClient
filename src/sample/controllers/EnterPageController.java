package sample.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import sample.models.app.fields.EnterModel;
import sample.models.app.Person;
import sample.models.json.User;
import sample.utils.alerts.AlertsUtil;
import sample.utils.validations.ValidUtil;
import sample.utils.requests.RequestsUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class EnterPageController extends EnterModel {
    private final Gson gson = new Gson();
    private Person person;

    @FXML
    private void initialize() {
        userLogEmailField.setText(null);
        passwordField.setText(null);
        person = new Person();
    }

    @FXML
    private void handleEnter() {
        if (passwordToggle.isSelected())
            passwordField.setText(visiblePasswordField.getText());
        if (ValidUtil.isInputValidEnter(this, dialStage)) {
            if (ValidUtil.isRegExValidEnter(this, dialStage)) {
                RequestsUtil requestsUtil = new RequestsUtil("/enter", "POST");
                requestsUtil.setParams(new HashMap<>() {{
                    put("login_email", userLogEmailField.getText());
                    put("password", passwordField.getText());
                }});
                requestsUtil.thread.start();
                RequestsUtil.runningThread(requestsUtil, dialStage);
                if (!Objects.equals(requestsUtil.getResponse(), "") && requestsUtil.getResponse() != null) {
                    User user = gson.fromJson(requestsUtil.getResponse(), User.class);
                    person.setId(user.getId());
                    person.setFirstName(user.getFirst_name());
                    person.setLastName(user.getLast_name());
                    person.setLogin(user.getLogin());
                    person.setEmail(user.getEmail());
                    person.setPhoneNumber(user.getPhone_number());
                    if (user.getBirthday() == null) person.setBirthday(null);
                    else person.setBirthday(LocalDate.parse(user.getBirthday()));
                    person.setPassword(passwordField.getText());
                    person.setRepeatPassword(passwordField.getText());
                    dialStage.close();
                } else if (!requestsUtil.getDisconnect()
                        && Objects.equals(requestsUtil.getResponse(), ""))
                    AlertsUtil.showNoValidEnterAlert(dialStage);
            }
        }
    }

    public Person getPerson() { return person; }
}
