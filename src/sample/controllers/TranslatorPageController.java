package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Person;

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

    private Stage dialStage;
    private Person person;
    private Main main;
    private ToggleGroup groupMorse;
    private ToggleGroup groupLang;

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    public void setPerson(Person person) {
        this.person = person;
        loginField.setText(this.person.getLogin());
    }

    public void setMain(Main main) { this.main = main; }

    @FXML
    public void initialize() {
        groupMorse = new ToggleGroup();
        groupLang = new ToggleGroup();
        toMorseRadioButton.setToggleGroup(groupMorse);
        fromMorseRadioButton.setToggleGroup(groupMorse);
        rusRadioButton.setToggleGroup(groupLang);
        engRadioButton.setToggleGroup(groupLang);
    }

    @FXML
    public void handlerTranslate() {
        // TODO: Запрос на перевод текста
        endStringArea.setText(startStringArea.getText());
    }

    @FXML
    public void handlerExitPC() {
        person = null;
        dialStage.close();
    }

    @FXML
    public void handlerUpdatePC() {
        EditPersonPageController editPersonPageController = main.showEditPersonPage(this.dialStage, this.person);
        if (editPersonPageController != null) {
            if (editPersonPageController.isDelete()) {
                this.person = null;
                dialStage.close();
            }
            else {
                this.person = editPersonPageController.getPerson();
                loginField.setText(this.person.getLogin());
            }
        }
    }
}
