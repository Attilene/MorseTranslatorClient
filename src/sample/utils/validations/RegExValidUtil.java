package sample.utils.validations;

import java.util.regex.Pattern;

/**
 * Class for methods which checking right format of fields with regular expressions
 *
 * @author  Artem Bakanov aka Attilene
 */
public final class RegExValidUtil {
    /**
     * Pattern: ^[^%"';:]*$
     */
    private static final String STANDARD_REGEX = "^[^%\"';:]*$";

    /**
     * Pattern: ^([a-zA-Z0-9_-]+\.)*[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)*\.[a-zA-Z]{2,6}$
     */
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_-]+\\.)*[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)*\\.[a-zA-Z]{2,6}$";

    /**
     * Pattern: ^((?=.*[0-9])(?=.*[a-zа-я])(?=.*[A-ZА-Я])(?=.*[@#$]).{8,})$
     */
    private static final String PASSWORD_REGEX = "^((?=.*[0-9])(?=.*[a-zа-я])(?=.*[A-ZА-Я])(?=.*[@#$]).{8,})$";

    /**
     * Pattern: ^[(+7)8]+([0-9]{10})$
     */
    private static final String PHONE_NUMBER_REGEX = "^[(+7)8]+([0-9]{10})$";

    private RegExValidUtil() {}

    /**
     * Checking format of field with STANDARD_REGEX:
     * ^[^%"';:]*$
     *
     * @param   text  string to be checked
     * @return        true, if string matches with regular expression
     */
    public static boolean checkStandard(String text) { return Pattern.matches(STANDARD_REGEX, text); }

    /**
     * Checking format of field with EMAIL_REGEX:
     * ^([a-zA-Z0-9_-]+\.)*[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)*\.[a-zA-Z]{2,6}$
     *
     * @param   email  email string to be checked
     * @return         true, if string matches with regular expression
     */
    public static boolean checkEmail(String email) { return Pattern.matches(EMAIL_REGEX, email); }

    /**
     * Checking format of field with PASSWORD_REGEX:
     * ^((?=.*[0-9])(?=.*[a-zа-я])(?=.*[A-ZА-Я])(?=.*[@#$]).{8,})$
     *
     * @param   password  password string to be checked
     * @return            true, if string matches with regular expression
     */
    public static boolean checkPassword(String password) { return Pattern.matches(PASSWORD_REGEX, password); }

    /**
     * Checking format of field with PHONE_NUMBER_REGEX:
     * ^[(+7)8]+([0-9]{10})$
     *
     * @param   phoneNumber  phone number string to be checked
     * @return               true, if string matches with regular expression
     */
    public static boolean checkPhoneNumber(String phoneNumber) { return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber); }

    /**
     * Getter for EMAIL_REGEX pattern
     *
     * @return  EMAIL_REGEX pattern
     */
    public static String getEmailRegex() { return EMAIL_REGEX; }

    /**
     * Getter for PASSWORD_REGEX pattern
     *
     * @return  PASSWORD_REGEX pattern
     */
    public static String getPasswordRegex() { return PASSWORD_REGEX; }

    /**
     * Getter for PHONE_NUMBER_REGEX pattern
     *
     * @return  PHONE_NUMBER_REGEX pattern
     */
    public static String getPhoneNumberRegex() { return PHONE_NUMBER_REGEX; }

    /**
     * Getter for STANDARD_REGEX pattern
     *
     * @return  STANDARD_REGEX pattern
     */
    public static String getStandardRegex() { return STANDARD_REGEX; }
}
