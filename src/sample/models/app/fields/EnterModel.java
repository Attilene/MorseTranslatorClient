package sample.models.app.fields;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public abstract class EnterModel {
    @FXML
    public TextField userLogEmailField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField visiblePasswordField;

    @FXML
    public CheckBox passwordToggle;

    protected Stage dialStage;

    @FXML
    public void handleToggleVisiblePassword() {
        if (passwordToggle.isSelected()) {
            visiblePasswordField.setText(passwordField.getText());
            visiblePasswordField.setVisible(true);
            passwordField.setVisible(false);
        } else {
            passwordField.setText(visiblePasswordField.getText());
            passwordField.setVisible(true);
            visiblePasswordField.setVisible(false);
        }
    }

    @FXML
    private void handleCancel() { dialStage.close(); }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }
}
