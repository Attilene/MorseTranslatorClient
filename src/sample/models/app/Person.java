package sample.models.app;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Person model class for output person data to application forms
 *
 * @author  Artem Bakanov aka Attilene
 */
public class Person {
    /**
     * Unique identifier of Person entity for output to application forms
     */
    private final LongProperty id;

    /**
     * First name of Person entity for output to application forms
     */
    private final StringProperty firstName;

    /**
     * Last name of Person entity for output to application forms
     */
    private final StringProperty lastName;

    /**
     * Login of Person entity for output to application forms
     */
    private final StringProperty login;

    /**
     * Email of Person entity for output to application forms
     */
    private final StringProperty email;

    /**
     * Phone number of Person entity for output to application forms
     */
    private final StringProperty phoneNumber;

    /**
     * Birthday of Person entity for output to application forms
     */
    private final ObjectProperty<LocalDate> birthday;

    /**
     * Password of Person entity for output to application forms
     */
    private final StringProperty password;

    /**
     * Repeat password of Person entity for output to application forms
     */
    private final StringProperty repeatPassword;

    /**
     * Full constructor for writing user data to fields of instance Person class
     *
     * @param  firstName       person`s first name from the field of the same name
     * @param  lastName        person`s last name from the field of the same name
     * @param  login           person`s login from the field of the same name
     * @param  email           person`s email from the field of the same name
     * @param  phoneNumber     person`s phone number from the field of the same name
     * @param  birthday        person`s birthday from the field of the same name
     * @param  password        person`s password from the field of the same name
     * @param  repeatPassword  person`s repeat password from the field of the same name
     */
    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  String phoneNumber,
                  LocalDate birthday,
                  String password,
                  String repeatPassword) {
        this.id = new SimpleLongProperty();
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.login = new SimpleStringProperty(login);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.birthday = new SimpleObjectProperty<>(birthday);
        this.password = new SimpleStringProperty(password);
        this.repeatPassword = new SimpleStringProperty(repeatPassword);
    }

    /**
     * Constructor for writing user data without birthday to fields of instance Person class
     *
     * @param  firstName       person`s first name from the field of the same name
     * @param  lastName        person`s last name from the field of the same name
     * @param  login           person`s login from the field of the same name
     * @param  email           person`s email from the field of the same name
     * @param  phoneNumber     person`s phone number from the field of the same name
     * @param  password        person`s password from the field of the same name
     * @param  repeatPassword  person`s repeat password from the field of the same name
     */
    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  String phoneNumber,
                  String password,
                  String repeatPassword) {
        this(
                firstName,
                lastName,
                login,
                email,
                phoneNumber,
                null,
                password,
                repeatPassword
        );
    }

    /**
     * Constructor for writing user data without phone number to fields of instance Person class
     *
     * @param  firstName       person`s first name from the field of the same name
     * @param  lastName        person`s last name from the field of the same name
     * @param  login           person`s login from the field of the same name
     * @param  email           person`s email from the field of the same name
     * @param  birthday        person`s birthday from the field of the same name
     * @param  password        person`s password from the field of the same name
     * @param  repeatPassword  person`s repeat password from the field of the same name
     */
    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  LocalDate birthday,
                  String password,
                  String repeatPassword) {
        this(
                firstName,
                lastName,
                login,
                email,
                null,
                birthday,
                password,
                repeatPassword
        );
    }

    /**
     * Constructor for writing user data without phone number and birthday to fields of instance Person class
     *
     * @param  firstName       person`s first name from the field of the same name
     * @param  lastName        person`s last name from the field of the same name
     * @param  login           person`s login from the field of the same name
     * @param  email           person`s email from the field of the same name
     * @param  password        person`s password from the field of the same name
     * @param  repeatPassword  person`s repeat password from the field of the same name
     */
    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  String password,
                  String repeatPassword) {
        this(
                firstName,
                lastName,
                login,
                email,
                null,
                null,
                password,
                repeatPassword
        );
    }

    /**
     * Constructor for writing null data to fields of instance Person class
     */
    public Person() {
        this(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        );
    }

    /**
     * Getter for first name field
     *
     * @return  Person first name
     */
    public String getFirstName() { return firstName.get(); }

    /**
     * Getter for last name field
     *
     * @return  Person last name
     */
    public String getLastName() { return lastName.get(); }

    /**
     * Getter for login field
     *
     * @return  Person login
     */
    public String getLogin() { return login.get(); }

    /**
     * Getter for email field
     *
     * @return  Person email
     */
    public String getEmail() { return email.get(); }

    /**
     * Getter for phone number field
     *
     * @return  Person phone number
     */
    public String getPhoneNumber() { return phoneNumber.get(); }

    /**
     * Getter for birthday field
     *
     * @return  Person birthday
     */
    public LocalDate getBirthday() { return birthday.get(); }

    /**
     * Getter for password field
     *
     * @return  Person password
     */
    public String getPassword() { return password.get(); }

    /**
     * Getter for repeat password field
     *
     * @return  Person repeat password
     */
    public String getRepeatPassword() { return repeatPassword.get(); }

    /**
     * Getter for unique identifier field
     *
     * @return  Person id
     */
    public Long getId() { return id.get(); }

    /**
     * Setter for first name field
     *
     * @param  firstName  Person first name
     */
    public void setFirstName(String firstName) { this.firstName.set(firstName); }

    /**
     * Setter for last name field
     *
     * @param  lastName  Person last name
     */
    public void setLastName(String lastName) { this.lastName.set(lastName); }

    /**
     * Setter for login field
     *
     * @param  login  Person login
     */
    public void setLogin(String login) { this.login.set(login); }

    /**
     * Setter for email field
     *
     * @param  email  Person email
     */
    public void setEmail(String email) { this.email.set(email); }

    /**
     * Setter for phone number field
     *
     * @param  phoneNumber  Person phone number
     */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber.set(phoneNumber); }

    /**
     * Setter for birthday field
     *
     * @param  birthday  Person birthday
     */
    public void setBirthday(LocalDate birthday) { this.birthday.set(birthday); }

    /**
     * Setter for first name field
     *
     * @param  password  Person password
     */
    public void setPassword(String password) { this.password.set(password); }

    /**
     * Setter for repeat password field
     *
     * @param  repeatPassword  Person repeat password
     */
    public void setRepeatPassword(String repeatPassword) { this.repeatPassword.set(repeatPassword); }

    /**
     * Setter for unique identifier field
     *
     * @param  id  Person id
     */
    public void setId(Long id) { this.id.set(id); }

    /**
     * Outputting fields of Person class in a string format
     *
     * @return  string field
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id.get() +
                ", firstName=" + firstName.get() +
                ", lastName=" + lastName.get() +
                ", login=" + login.get() +
                ", email=" + email.get() +
                ", phoneNumber=" + phoneNumber.get() +
                ", birthday=" + birthday.get() +
                ", password=" + password.get() +
                ", repeatPassword=" + repeatPassword.get() +
                '}';
    }
}
