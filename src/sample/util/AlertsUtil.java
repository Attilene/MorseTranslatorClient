package sample.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertsUtil {
    public static void showDeleteProfileConfirmationAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.setTitle("Удаление профиля");
        alert.setHeaderText("Вы уверены, что хотите удалить свой профиль?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public static void showInputValidAlert(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Недопустимые поля");
        alert.setHeaderText("Пожалуйста, исправьте недопустимые поля");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
