package sample.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Person;

import java.util.Optional;

public class AlertsUtil {
    public static boolean showDeleteProfileConfirmationAlert(Stage stage, Main main, Person person) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.setTitle("Удаление профиля");
        alert.setHeaderText("Вы уверены, что хотите удалить свой профиль?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // TODO: запрос на удаление профиля клиента
            main.getPersonData().remove(person);
            alert.close();
            stage.close();
            return true;
        } else {
            alert.close();
            return false;
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

    public static void showNoValidEnterAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Ошибка входа");
        alert.setHeaderText("Недействительные логин/почта или пароль!");
        alert.setContentText("Введите корректные данные или зарегистрируйтесь");
        alert.showAndWait();
    }

    public static void showInternalServerErrorAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Ошибка сервера");
        alert.setHeaderText("Сервер временно недоступен!");
        alert.setContentText("Свяжитесь с администратором или повторите попытку позже");
        alert.showAndWait();
    }
}
