package sample.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import sample.models.app.fields.EnterModel;
import sample.models.app.Person;
import sample.models.json.User;
import sample.utils.alerts.AlertsUtil;
import sample.utils.validations.RegExValidUtil;
import sample.utils.validations.ValidUtil;
import sample.utils.requests.RequestsUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

/**
 * Controller for managing enterPage.fxml form
 *
 * @see     EnterModel
 * @author  Artem Bakanov aka Attilene
 */
public class EnterPageController extends EnterModel {
    /**
     * Google tool for converting data to json format and back
     */
    private final Gson gson = new Gson();

    /**
     * User`s personal data wrapped into instance of Person model class
     */
    private Person person;

    /**
     * The initializing method of the controller, which is started
     * at creation of instance of EnterPageController class
     */
    @FXML
    private void initialize() {
        userLogEmailField.setText(null);
        passwordField.setText(null);
        person = new Person();
    }

    /**
     * Method for enter button for log in user`s private cabinet
     */
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

    /**
     * Method for sending mail on user`s email address
     * for restore forgotten password
     */
    @FXML
    public void handleRestorePassword() {
        if (userLogEmailField.getText() != null) {
            if (RegExValidUtil.checkEmail(userLogEmailField.getText())) {
                RequestsUtil requestsUtil = new RequestsUtil("/enter/check_email", "POST");
                requestsUtil.setParams(new HashMap<>() {{
                    put("email", userLogEmailField.getText());
                }});
                requestsUtil.thread.start();
                RequestsUtil.runningThread(requestsUtil, dialStage);
                if (Objects.equals(requestsUtil.getResponse(), "mail_send_success"))
                    AlertsUtil.showMailSentSuccessfully(dialStage, userLogEmailField.getText());
                else if (!requestsUtil.getDisconnect()
                        && Objects.equals(requestsUtil.getResponse(), "mail_send_failed"))
                    AlertsUtil.showUserNotExistAlert(dialStage);
            } else
                AlertsUtil.showIncorrectEmailAlert(dialStage);
        } else
            AlertsUtil.showIncorrectEmailAlert(dialStage);
    }

    /**
     * Getter for user`s personal data
     *
     * @return  instance of Person model contained user`s data
     */
    public Person getPerson() { return person; }
}
