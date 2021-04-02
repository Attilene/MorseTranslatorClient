package sample.models.app.fields;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Abstract class for creating model for edit or registration application form windows
 *
 * @author  Artem Bakanov aka Attilene
 */
public abstract class RegistrationEditModel {
    /**
     * TextField for writing user`s first name
     */
    @FXML
    public TextField firstNameField;

    /**
     * TextField for writing user`s last name
     */
    @FXML
    public TextField lastNameField;

    /**
     * TextField for writing user`s login
     */
    @FXML
    public TextField loginField;

    /**
     * TextField for writing user`s email address
     */
    @FXML
    public TextField emailField;

    /**
     * TextField for writing user`s phone number
     */
    @FXML
    public TextField phoneNumberField;

    /**
     * DatePicker for appointing user`s birthday
     */
    @FXML
    public DatePicker birthdayField;

    /**
     * PasswordField for writing hidden user`s password
     */
    @FXML
    public PasswordField passwordField;

    /**
     * PasswordField for writing hidden user`s repeat password
     */
    @FXML
    public PasswordField repeatPasswordField;

    /**
     * TextField for writing user`s open password
     */
    @FXML
    public TextField visiblePasswordField;

    /**
     * TextField for writing user`s open repeat password
     */
    @FXML
    public TextField visibleRepeatPasswordField;

    /**
     * CheckBox for opening or hiding user`s password and repeat password
     */
    @FXML
    public CheckBox passwordToggle;

    /**
     * Window where displaying fxml form
     */
    protected Stage dialStage;

    /**
     * Method for hiding or opening user`s password and repeat password
     */
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

    /**
     * Close the application window
     */
    @FXML
    private void handleCancel() { dialStage.close(); }

    /**
     * Setter for window`s stage
     *
     * @param  dialStage  window`s stage
     */
    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }
}
