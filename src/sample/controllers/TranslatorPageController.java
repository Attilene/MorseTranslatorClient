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

/**
 * Controller for managing translatorPage.fxml form
 *
 * @author  Artem Bakanov aka Attilene
 */
public class TranslatorPageController {
    /**
     * TextArea for writing the initial string to translate to Morse code or back
     */
    @FXML
    private TextArea startStringArea;

    /**
     * TextArea for writing the resulting translation string
     */
    @FXML
    private TextArea endStringArea;

    /**
     * TextField for writing user`s login
     */
    @FXML
    private TextField loginField;

    /**
     * RadioButton to set the translation result to be a Morse code string
     */
    @FXML
    private RadioButton toMorseRadioButton;

    /**
     * RadioButton to set the translation result to be a natural language
     */
    @FXML
    private RadioButton fromMorseRadioButton;

    /**
     * RadioButton to establish Russian as the main natural language
     */
    @FXML
    private RadioButton rusRadioButton;

    /**
     * RadioButton to establish English as the main natural language
     */
    @FXML
    private RadioButton engRadioButton;


    /**
     * ToggleGroup for combining toMorseRadioButton and fromMorseRadioButton into one common area
     */
    private final ToggleGroup groupMorse = new ToggleGroup();

    /**
     * ToggleGroup for combining rusRadioButton and engRadioButton into one common area
     */
    private final ToggleGroup groupLang = new ToggleGroup();

    /**
     * Google tool for converting data to json format and back
     */
    private final Gson gson = new Gson();

    /**
     * Window where displaying fxml form
     */
    private Stage dialStage;

    /**
     * User`s personal data wrapped into instance of Person model class
     */
    private Person person;

    /**
     * Instance of Main class created for showing child forms
     */
    private Main main;

    /**
     * The initializing method of the controller, which is started
     * at creation of instance of TranslatorPageController class
     */
    @FXML
    public void initialize() {
        toMorseRadioButton.setToggleGroup(groupMorse);
        fromMorseRadioButton.setToggleGroup(groupMorse);
        rusRadioButton.setToggleGroup(groupLang);
        engRadioButton.setToggleGroup(groupLang);
        startStringArea.setWrapText(true);
        endStringArea.setWrapText(true);
    }

    /**
     * Method for translating string in startStringArea in Russian or English
     * to Morse code or back and output the result to endStringArea
     */
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

    /**
     * Method for update button for opening historyPage.fxml
     */
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

    /**
     * Method for history button for getting list of records
     * of user`s translate history and opening historyPage.fxml
     */
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

    /**
     * Method for closing user`s private cabinet and current fxml form too
     */
    @FXML
    public void handlerExitPC() {
        person = null;
        dialStage.close();
        System.gc();
    }

    /**
     * Setter for window`s stage
     *
     * @param  dialStage  window`s stage
     */
    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    /**
     * Setter for user`s personal data
     *
     * @param  person  user`s personal data wrapped into instance of Person model class
     */
    public void setPerson(Person person) {
        this.person = person;
        loginField.setText(this.person.getLogin());
    }

    /**
     * Setter for instance of Main class
     *
     * @param  main  instance of Main class
     */
    public void setMain(Main main) { this.main = main; }
}
