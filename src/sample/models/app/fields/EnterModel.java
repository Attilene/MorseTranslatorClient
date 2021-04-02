package sample.models.app.fields;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Abstract class for creating model for enter`s application form window
 *
 * @author  Artem Bakanov aka Attilene
 */
public abstract class EnterModel {
    /**
     * TextField for writing user`s login or email address
     */
    @FXML
    public TextField userLogEmailField;

    /**
     * PasswordField for writing hidden user`s password
     */
    @FXML
    public PasswordField passwordField;

    /**
     * TextField for writing user`s open password
     */
    @FXML
    public TextField visiblePasswordField;

    /**
     * CheckBox for opening or hiding user`s password
     */
    @FXML
    public CheckBox passwordToggle;

    /**
     * Window where displaying fxml form
     */
    protected Stage dialStage;

    /**
     * Method for hiding or opening user`s password
     */
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
