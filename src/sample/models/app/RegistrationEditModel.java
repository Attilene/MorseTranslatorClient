package sample.models.app;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
}
