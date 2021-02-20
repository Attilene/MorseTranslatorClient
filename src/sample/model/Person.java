package sample.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Person {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty login;
    private final StringProperty email;
    private final StringProperty phoneNumber;
    private final ObjectProperty<LocalDate> birthday;

    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  String phoneNumber,
                  LocalDate birthday) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.login = new SimpleStringProperty(login);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.birthday = new SimpleObjectProperty<>(birthday);
    }

    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  String phoneNumber) {
        this(firstName, lastName, login, email, phoneNumber, null);
    }

    public Person(String firstName,
                  String lastName,
                  String login,
                  String email,
                  LocalDate birthday) {
        this(firstName, lastName, login, email, null, birthday);
    }

    public Person(String firstName,
                  String lastName,
                  String login,
                  String email) {
        this(firstName, lastName, login, email, null, null);
    }

    public Person() {
        this(null, null, null, null, null, null);
    }

    public String getFirstName() { return firstName.get(); }

    public String getLastName() { return lastName.get(); }

    public String getLogin() { return login.get(); }

    public String getEmail() { return email.get(); }

    public String getPhoneNumber() { return phoneNumber.get(); }

    public LocalDate getBirthday() { return birthday.get(); }

    public void setFirstName(String firstName) { this.firstName.set(firstName); }

    public void setLastName(String lastName) { this.lastName.set(lastName); }

    public void setLogin(String login) { this.login.set(login); }

    public void setEmail(String email) { this.email.set(email); }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber.set(phoneNumber); }

    public void setBirthday(LocalDate birthday) { this.birthday.set(birthday); }
}
