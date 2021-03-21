package sample.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sample.Main;
import sample.models.app.Person;
import sample.models.json.History;
import sample.utils.alerts.AlertsUtil;
import sample.utils.requests.RequestsUtil;

import java.util.HashMap;
import java.util.Objects;

public class TranslatorPageController {
    @FXML
    private TextArea startStringArea;
    @FXML
    private TextArea endStringArea;
    @FXML
    private TextField loginField;
    @FXML
    private RadioButton toMorseRadioButton;
    @FXML
    private RadioButton fromMorseRadioButton;
    @FXML
    private RadioButton rusRadioButton;
    @FXML
    private RadioButton engRadioButton;

    private final ToggleGroup groupMorse = new ToggleGroup();
    private final ToggleGroup groupLang = new ToggleGroup();
    private final Gson gson = new Gson();
    private Stage dialStage;
    private Person person;
    private Main main;

    @FXML
    public void initialize() {
        toMorseRadioButton.setToggleGroup(groupMorse);
        fromMorseRadioButton.setToggleGroup(groupMorse);
        rusRadioButton.setToggleGroup(groupLang);
        engRadioButton.setToggleGroup(groupLang);
        startStringArea.setWrapText(true);
        endStringArea.setWrapText(true);
    }

    @FXML
    public void handlerTranslate() {
        if (startStringArea.getText() != null) {
            RequestsUtil requestsUtil = new RequestsUtil("/history", "POST");
            requestsUtil.setParams(new HashMap<>() {{
                put("start_string", startStringArea.getText());
                put("user_id", person.getId().toString());
                if (toMorseRadioButton.isSelected()) put("morse", "true");
                else put("morse", "false");
                if (engRadioButton.isSelected()) put("language", "true");
                else put("language", "false");
            }});
            requestsUtil.thread.start();
            RequestsUtil.runningThread(requestsUtil, dialStage);
            if (!Objects.equals(requestsUtil.getResponse(), "") && requestsUtil.getResponse() != null) {
                History history = gson.fromJson(requestsUtil.getResponse(), History.class);
                endStringArea.setText(history.getEnd_string());
            } else if (!requestsUtil.getDisconnect()
                    && Objects.equals(requestsUtil.getResponse(), ""))
                AlertsUtil.showFailedTranslateAlert(dialStage);
        }
    }

    @FXML
    public void handlerUpdatePC() {
        EditPersonPageController editPersonPageController = main.showEditPersonPage(this.dialStage, this.person);
        if (editPersonPageController != null) {
            if (editPersonPageController.isDelete()) {
                this.person = null;
                dialStage.close();
                System.gc();
            } else {
                this.person = editPersonPageController.getPerson();
                loginField.setText(this.person.getLogin());
            }
        }
    }

    @FXML
    public void handleHistory() {
        RequestsUtil requestsUtil = new RequestsUtil("/histories", "POST");
        requestsUtil.setParams(new HashMap<>() {{
            put("user_id", person.getId().toString());
        }});
        requestsUtil.thread.start();
        RequestsUtil.runningThread(requestsUtil, dialStage);
        if (!Objects.equals(requestsUtil.getResponse(), "") && requestsUtil.getResponse() != null) {
            main.showHistoryPage(dialStage, gson.fromJson(requestsUtil.getResponse(), History[].class));
        } else if (!requestsUtil.getDisconnect()
                && Objects.equals(requestsUtil.getResponse(), ""))
            AlertsUtil.showInternalServerErrorAlert(dialStage);
    }

    @FXML
    public void handlerExitPC() {
        person = null;
        dialStage.close();
        System.gc();
    }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    public void setPerson(Person person) {
        this.person = person;
        loginField.setText(this.person.getLogin());
    }

    public void setMain(Main main) { this.main = main; }
}
