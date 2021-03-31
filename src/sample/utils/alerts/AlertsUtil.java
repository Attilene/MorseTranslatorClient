package sample.utils.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Class that contained alerts of JavaFX application Morse Translator
 *
 * @author  Artem Bakanov aka Attilene
 */
public final class AlertsUtil {
    private AlertsUtil() {}

    /**
     * Confirmation alert about deleting user`s private cabinet
     *
     * @param   stage  window where displaying alert
     * @return         true, if button "OK" is pressed, or false, if if any other button is pressed
     */
    public static boolean showDeleteProfileConfirmationAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.setTitle("Удаление профиля");
        alert.setHeaderText("Вы уверены, что хотите удалить свой профиль?");
        for (ButtonType type: alert.getButtonTypes())
            ((Button) alert.getDialogPane().lookupButton(type)).setDefaultButton(type == ButtonType.CANCEL);
        addStyleSheetToAlert(alert);
        return alert.showAndWait().get() == ButtonType.OK;
    }

    /**
     * Information alert about sending recovery message to the email address
     *
     * @param  stage  window where displaying alert
     * @param  email  user`s email address
     */
    public static void showMailSentSuccessfully(Stage stage, String email) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("Письмо отправлено");
        alert.setHeaderText("Письмо для восстановления пароля успешно отправлено!");
        alert.setContentText("Проверьте входящие сообщения и спам на электронной почте: " + email);
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert about missing values in fields
     *
     * @param  stage    window where displaying alert
     * @param  message  information about empty fields
     */
    public static void showInputValidAlert(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Неправильно заполненные поля");
        alert.setHeaderText("Исправьте неправильно заполненные поля");
        alert.setContentText(message);
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert about incorrect filling in fields
     *
     * @param  stage  window where displaying alert
     * @param  name   name of the field
     */
    public static void showWrongFormatStandardAlert(Stage stage, String name) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Неверный формат строки");
        alert.setHeaderText("Неправильный формат текстового поля: " + name);
        alert.setContentText("Не допускаются символы: % \" ' ; :");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert about failing sign in
     *
     * @param  stage  window where displaying alert
     */
    public static void showNoValidEnterAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Ошибка входа");
        alert.setHeaderText("Недействительные логин/почта или пароль!");
        alert.setContentText("Введите корректные данные или зарегистрируйтесь");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert about incorrect format of the email address
     *
     * @param  stage  window where displaying alert
     */
    public static void showIncorrectEmailAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Неправильный формат почты");
        alert.setHeaderText("Введите корректную электронную почту!");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert about incorrect format of the password
     *
     * @param  stage  window where displaying alert
     */
    public static void showWrongFormatPasswordAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Неправильный формат пароля");
        alert.setHeaderText("Введите пароль в нужном формате");
        alert.setContentText("""
                Длина пароля: не менее 8 символов
                Пароль должен содержать:
                заглавные буквы, строчные буквы, цифры,
                специальные символы из списка: @ # $""");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert about incorrect format of the phone number
     *
     * @param  stage  window where displaying alert
     */
    public static void showWrongFormatPhoneNumberAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Неправильный формат номера телефона");
        alert.setHeaderText("Введите номер телефона в нужном формате");
        alert.setContentText("Формат: +7{номер телефона} или 8{номер телефона}");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert about exceeding the allowed field length
     *
     * @param  stage    window where displaying alert
     * @param  name     name of the field
     * @param  symbols  acceptable length of the string
     */
    public static void showBigStringAlert(Stage stage, String name, int symbols) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Превышение длины строки");
        alert.setHeaderText("Длина поля " + name + " больше " + symbols + " символов!");
        alert.setContentText("Измените значение текстового поля");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert that user is already exist
     *
     * @param  stage  window where displaying alert
     */
    public static void showUserExistAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Пользователь существует");
        alert.setHeaderText("Пользователь с такими данными уже существует!");
        alert.setContentText("Логин, почта или номер телефона должны быть уникальными");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert that user does not exist yet
     *
     * @param  stage  window where displaying alert
     */
    public static void showUserNotExistAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Пользователь не существует");
        alert.setHeaderText("Пользователь с такой электронной почтой не существует!");
        alert.setContentText("Введите корректную почту");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert that user does not exist yet
     *
     * @param  stage  window where displaying alert
     */
    public static void showNotSelectedHistory(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(stage);
        alert.setTitle("Ничего не выбрано!");
        alert.setHeaderText("История перевода не выбрана!");
        alert.setContentText("Пожалуйста, выберите историю!");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert that the server is temporarily unavailable
     *
     * @param  stage  window where displaying alert
     */
    public static void showInternalServerErrorAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Ошибка сервера");
        alert.setHeaderText("Сервер временно недоступен!");
        alert.setContentText("Свяжитесь с администратором или повторите попытку позже");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Error alert about failed text translation attempt
     *
     * @param  stage  window where displaying alert
     */
    public static void showFailedTranslateAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Перевод не удался");
        alert.setHeaderText("Введите правильную строку для перевода!");
        alert.setContentText("Строка не должна быть пустой.\nЯзык строки должен соответствовать переключателям");
        addStyleSheetToAlert(alert);
        alert.showAndWait();
    }

    /**
     * Adding style to alert message
     *
     * @param  alert  alert to attach the css-file to
     */
    public static void addStyleSheetToAlert(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.getStylesheets().add
                (AlertsUtil.class.getResource("../../resources/styles/DialogStyle.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog-pane");
    }
}
