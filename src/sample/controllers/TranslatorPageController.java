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
import sample.models.json.JsonHistory;
import sample.models.to.dict.DictTranslator;
import sample.utils.AlertsUtil;
import sample.utils.requests.PostRequestUtil;
import sample.utils.requests.RequestsUtil;

import java.util.ArrayList;
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

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    public void setPerson(Person person) {
        this.person = person;
        loginField.setText(this.person.getLogin());
    }

    public void setMain(Main main) { this.main = main; }

    @FXML
    public void initialize() {
        toMorseRadioButton.setToggleGroup(groupMorse);
        fromMorseRadioButton.setToggleGroup(groupMorse);
        rusRadioButton.setToggleGroup(groupLang);
        engRadioButton.setToggleGroup(groupLang);
    }

    @FXML
    public void handlerTranslate() {
        PostRequestUtil postRequestUtil = new PostRequestUtil("/history");
        postRequestUtil.setParams(new DictTranslator().setParams(new ArrayList<>() {{
            add(startStringArea.getText());
            add(person.getId().toString());
            if (toMorseRadioButton.isSelected()) add("true");
            else add("false");
            if (engRadioButton.isSelected()) add("true");
            else add("false");
        }}));
        postRequestUtil.thread.start();
        RequestsUtil.runningThread(postRequestUtil, dialStage);
        if (!Objects.equals(postRequestUtil.getResponse(), "")) {
            JsonHistory jsonHistory = gson.fromJson(postRequestUtil.getResponse(), JsonHistory.class);
            endStringArea.setText(jsonHistory.getEnd_string());
        } else if (!postRequestUtil.getDisconnect()
                && Objects.equals(postRequestUtil.getResponse(), ""))
            AlertsUtil.showTranslateFailedAlert(dialStage);
    }

    @FXML
    public void handlerExitPC() {
        person = null;
        dialStage.close();
        System.gc();
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
}
