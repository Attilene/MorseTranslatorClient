package sample.model;

import javafx.beans.property.*;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty login;
    private final StringProperty email;
    private final StringProperty phoneNumber;
    private final ObjectProperty<LocalDate> birthday;
    private final StringProperty password;
    private final StringProperty repeatPassword;

    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  String phoneNumber,
                  LocalDate birthday,
                  String password,
                  String repeatPassword) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.login = new SimpleStringProperty(login);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.birthday = new SimpleObjectProperty<>(birthday);
        this.password = new SimpleStringProperty(password);
        this.repeatPassword = new SimpleStringProperty(repeatPassword);
    }

    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  String phoneNumber,
                  String password,
                  String repeatPassword) {
        this(firstName, lastName, login, email, phoneNumber, null, password, repeatPassword);
    }

    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  LocalDate birthday,
                  String password,
                  String repeatPassword) {
        this(firstName, lastName, login, email, null, birthday, password, repeatPassword);
    }

    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  String password,
                  String repeatPassword) {
        this(firstName, lastName, login, email, null, null, password, repeatPassword);
    }

    public Person() {
        this(null, null, null, null, null, null, null, null);
    }

    public String getFirstName() { return firstName.get(); }

    public String getLastName() { return lastName.get(); }

    public String getLogin() { return login.get(); }

    public String getEmail() { return email.get(); }

    public String getPhoneNumber() { return phoneNumber.get(); }

    public LocalDate getBirthday() { return birthday.get(); }

    public String getPassword() { return password.get(); }

    public String getRepeatPassword() { return repeatPassword.get(); }

    public void setFirstName(String firstName) { this.firstName.set(firstName); }

    public void setLastName(String lastName) { this.lastName.set(lastName); }

    public void setLogin(String login) { this.login.set(login); }

    public void setEmail(String email) { this.email.set(email); }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber.set(phoneNumber); }

    public void setBirthday(LocalDate birthday) { this.birthday.set(birthday); }

    public void setPassword(String password) { this.password.set(password); }

    public void setRepeatPassword(String repeatPassword) { this.repeatPassword.set(repeatPassword); }

    @Override
    public String toString() {
        return "Person{" +
                "firstName=" + firstName.get() +
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
