package sample.utils;

import java.util.regex.Pattern;

public abstract class RegExValidUtil {
    private static final String STANDARD_REGEX = "^[^%\"';:]*$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9]+@([a-zA-Z]{2,10}[.]){1,3}(com|by|ru|cc|net|ws)$";
    private static final String PASSWORD_REGEX = "^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,})$";
    private static final String PHONE_NUMBER_REGEX = "^[(+7)8]+([0-9]{10})$";

    public static boolean checkStandard(String text) { return Pattern.matches(STANDARD_REGEX, text); }

    public static boolean checkEmail(String email) { return Pattern.matches(EMAIL_REGEX, email); }

    public static boolean checkPassword(String password) { return Pattern.matches(PASSWORD_REGEX, password); }

    public static boolean checkPhoneNumber(String phoneNumber) { return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber); }

    public static String getEmailRegex() { return EMAIL_REGEX; }

    public static String getPasswordRegex() { return PASSWORD_REGEX; }

    public static String getPhoneNumberRegex() { return PHONE_NUMBER_REGEX; }

    public static String getStandardRegex() { return STANDARD_REGEX; }
}
