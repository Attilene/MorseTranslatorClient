package sample.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public abstract class AlertsUtil {
    public static boolean showDeleteProfileConfirmationAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.setTitle("Удаление профиля");
        alert.setHeaderText("Вы уверены, что хотите удалить свой профиль?");
        for (ButtonType type: alert.getButtonTypes())
            ((Button) alert.getDialogPane().lookupButton(type)).setDefaultButton(type == ButtonType.NO);
        addStyleSheetToAlert(alert);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage.close();
            return true;
        } else { return false; }
    }

    public static void showInputValidAlert(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Неправильно заполненные поля");
        alert.setHeaderText("Исправьте неправильно заполненные поля");
        alert.setContentText(message);
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    public static void showWrongFormatStandardAlert(Stage stage, String name) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Неверный формат строки");
        alert.setHeaderText("Неправильный формат текстового поля: " + name);
        alert.setContentText("Не допускаются символы: % \" ' ; :");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    public static void showNoValidEnterAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Ошибка входа");
        alert.setHeaderText("Недействительные логин/почта или пароль!");
        alert.setContentText("Введите корректные данные или зарегистрируйтесь");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    public static void showIncorrectEmailAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Неправильный формат почты");
        alert.setHeaderText("Введите корректную электронную почту!");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    public static void showWrongFormatPasswordAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Неправильный формат пароля");
        alert.setHeaderText("Введите пароль в нужном формате");
        alert.setContentText("""
                Длина пароля: не менее 8 символов
                Пароль должен содержать:
                заглавные буквы, строчные буквы, цифры,
                специальные символы из списка: @ # $ %""");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    public static void showWrongFormatPhoneNumberAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Неправильный формат номера телефона");
        alert.setHeaderText("Введите номер телефона в нужном формате");
        alert.setContentText("Формат: +7{номер телефона} или 8{номер телефона}");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    public static void showBigStringAlert(Stage stage, String name, int symbols) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Превышение длины строки");
        alert.setHeaderText("Длина поля " + name + " больше " + symbols + " символов!");
        alert.setContentText("Измените значение текстового поля");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    public static void showUserExistAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Пользователь существует");
        alert.setHeaderText("Пользователь с такими данными уже существует!");
        alert.setContentText("Логин, почта или номер телефона должны быть уникальными");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    public static void showInternalServerErrorAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Ошибка сервера");
        alert.setHeaderText("Сервер временно недоступен!");
        alert.setContentText("Свяжитесь с администратором или повторите попытку позже");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    public static void addStyleSheetToAlert(@NotNull Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                AlertsUtil.class.getResource("../resources/styles/DialogStyle.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog-pane");
    }
}
