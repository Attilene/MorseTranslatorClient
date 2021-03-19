package sample.utils;

import javafx.stage.Stage;
import sample.models.app.EnterModel;
import sample.models.app.RegistrationEditModel;

public final class ValidUtil {
    private ValidUtil() {}

    public static boolean isInputValidRegistrationEdit(RegistrationEditModel model, Stage dialStage) {
        String errorMessage = "";
        if (model.firstNameField.getText() == null || model.firstNameField.getText().length() == 0)
            errorMessage += "Нет имени!\n";
        if (model.lastNameField.getText() == null || model.lastNameField.getText().length() == 0)
            errorMessage += "Нет фамилии!\n";
        if (model.loginField.getText() == null || model.loginField.getText().length() == 0)
            errorMessage += "Нет логина!\n";
        if (model.emailField.getText() == null || model.emailField.getText().length() == 0)
            errorMessage += "Нет почты!\n";
        if (model.passwordField.getText() == null || model.passwordField.getText().length() == 0)
            errorMessage += "Нет пароля!\n";
        if (model.repeatPasswordField.getText() == null || model.repeatPasswordField.getText().length() == 0)
            errorMessage += "Нет повтора пароля!\n";
        if ((model.passwordField.getText() != null && model.repeatPasswordField.getText() != null) &&
                !model.passwordField.getText().equals(model.repeatPasswordField.getText()))
            errorMessage += "Повтор пароля не совпадает с паролем!\n";
        if (errorMessage.length() == 0) return true;
        else {
            AlertsUtil.showInputValidAlert(dialStage, errorMessage.substring(0, errorMessage.length() - 1));
            return false;
        }
    }

    public static boolean isInputValidEnter(EnterModel model, Stage dialStage) {
        String errorMessage = "";
        if (model.userLogEmailField.getText() == null || model.userLogEmailField.getText().length() == 0)
            errorMessage += "Нет логина/почты!\n";
        if (model.passwordField.getText() == null || model.passwordField.getText().length() == 0)
            errorMessage += "Нет пароля!\n";
        if (errorMessage.length() == 0) return true;
        else {
            AlertsUtil.showInputValidAlert(dialStage, errorMessage.substring(0, errorMessage.length() - 1));
            return false;
        }
    }

    public static boolean isInputValidLength(RegistrationEditModel model, Stage dialStage) {
        if (!ValidUtil.checkLength(model.firstNameField.getText(), 40))
            AlertsUtil.showBigStringAlert(dialStage, "Имя", 40);
        else if (!ValidUtil.checkLength(model.lastNameField.getText(), 40))
            AlertsUtil.showBigStringAlert(dialStage, "Фамилия", 40);
        else if (!ValidUtil.checkLength(model.loginField.getText(), 60))
            AlertsUtil.showBigStringAlert(dialStage, "Логин", 60);
        else if (!ValidUtil.checkLength(model.emailField.getText(), 50))
            AlertsUtil.showBigStringAlert(dialStage, "Email", 50);
        else
            return true;
        return false;
    }

    public static boolean isRegExValidEnter(EnterModel model, Stage dialStage) {
        if (RegExValidUtil.checkStandard(model.userLogEmailField.getText())
                || RegExValidUtil.checkEmail(model.userLogEmailField.getText()))
            return true;
        else {
            AlertsUtil.showNoValidEnterAlert(dialStage);
            return false;
        }
    }

    public static boolean isRegExValidRegistrationEdit(RegistrationEditModel model, Stage dialStage) {
        if (!RegExValidUtil.checkStandard(model.firstNameField.getText()))
            AlertsUtil.showWrongFormatStandardAlert(dialStage, "Имя");
        else if (!RegExValidUtil.checkStandard(model.lastNameField.getText()))
            AlertsUtil.showWrongFormatStandardAlert(dialStage, "Фамилия");
        else if (!RegExValidUtil.checkStandard(model.loginField.getText()))
            AlertsUtil.showWrongFormatStandardAlert(dialStage, "Логин");
        else if (!RegExValidUtil.checkEmail(model.emailField.getText()))
            AlertsUtil.showIncorrectEmailAlert(dialStage);
        else if (!RegExValidUtil.checkPassword(model.passwordField.getText()))
            AlertsUtil.showWrongFormatPasswordAlert(dialStage);
        else if ((model.phoneNumberField.getText() != null && model.phoneNumberField.getText().length() != 0)
                && !RegExValidUtil.checkPhoneNumber(model.phoneNumberField.getText()))
            AlertsUtil.showWrongFormatPhoneNumberAlert(dialStage);
        else
            return true;
        return false;
    }

    public static boolean checkLength(String text, int num) { return text.length() <= num; }
}
