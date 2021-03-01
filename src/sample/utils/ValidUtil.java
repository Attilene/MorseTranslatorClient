package sample.utils;

import java.util.regex.Pattern;

public abstract class ValidUtil {
    private static final String standardRegex = "^[^%\"';:]*$";
    private static final String emailRegex = "^[a-zA-Z0-9]+@([a-zA-Z]{2,10}[.]){1,3}(com|by|ru|cc|net|ws)$";
    private static final String passwordRegex = "^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,})$";
    private static final String phoneNumberRegex = "^[(+7)8]+([0-9]{10})$";

    public static boolean checkStandard(String text) { return Pattern.matches(standardRegex, text); }

    public static boolean checkEmail(String email) { return Pattern.matches(emailRegex, email); }

    public static boolean checkPassword(String password) { return Pattern.matches(passwordRegex, password); }

    public static boolean checkPhoneNumber(String phoneNumber) { return Pattern.matches(phoneNumberRegex, phoneNumber); }

    public static boolean checkLength(String text, int num) { return text.length() <= num; }

    public static String getEmailRegex() { return emailRegex; }

    public static String getPasswordRegex() { return passwordRegex; }

    public static String getPhoneNumberRegex() { return phoneNumberRegex; }

    public static String getStandardRegex() { return standardRegex; }
}
