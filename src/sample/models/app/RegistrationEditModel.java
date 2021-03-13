package sample.models.app;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public abstract class RegistrationEditModel {
    @FXML
    public TextField firstNameField;

    @FXML
    public TextField lastNameField;

    @FXML
    public TextField loginField;

    @FXML
    public TextField emailField;

    @FXML
    public TextField phoneNumberField;

    @FXML
    public DatePicker birthdayField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public PasswordField repeatPasswordField;

    @FXML
    public TextField visiblePasswordField;

    @FXML
    public TextField visibleRepeatPasswordField;

    @FXML
    public CheckBox passwordToggle;

    protected Stage dialStage;

    @FXML
    public void handleToggleVisiblePassword() {
        if (passwordToggle.isSelected()) {
            visiblePasswordField.setText(passwordField.getText());
            visibleRepeatPasswordField.setText(repeatPasswordField.getText());
            visiblePasswordField.setVisible(true);
            visibleRepeatPasswordField.setVisible(true);
            passwordField.setVisible(false);
            repeatPasswordField.setVisible(false);
        } else {
            passwordField.setText(visiblePasswordField.getText());
            repeatPasswordField.setText(visibleRepeatPasswordField.getText());
            passwordField.setVisible(true);
            repeatPasswordField.setVisible(true);
            visiblePasswordField.setVisible(false);
            visibleRepeatPasswordField.setVisible(false);
        }
    }

    @FXML
    private void handleCancel() { dialStage.close(); }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }
}
