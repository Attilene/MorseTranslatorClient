package sample.models.json;

import java.io.Serializable;
import java.util.Arrays;

/**
 * User model class for creating json-entity
 *
 * @author  Artem Bakanov aka Attilene
 */
public class User implements Serializable {
    /**
     * Unique identifier of User record from database
     */
    private Long id;

    /**
     * First name column
     */
    private String first_name;

    /**
     * Last name column
     */
    private String last_name;

    /**
     * Login column
     */
    private String login;

    /**
     * Email column
     */
    private String email;

    /**
     * Phone number column
     */
    private String phone_number;

    /**
     * Birthday column
     */
    private String birthday;

    /**
     * Instance of Password class which related to the user
     */
    private Password password;

    /**
     * Instances of Password class which related to the user
     */
    private History[] histories;

    /**
     * Getter for unique identifier field
     *
     * @return  User id
     */
    public Long getId() { return id; }

    /**
     * Getter for first name field
     *
     * @return  User first name
     */
    public String getFirst_name() { return first_name; }

    /**
     * Getter for last name field
     *
     * @return  User last name
     */
    public String getLast_name() { return last_name; }

    /**
     * Getter for phone number field
     *
     * @return  User phone number
     */
    public String getPhone_number() { return phone_number; }

    /**
     * Getter for login field
     *
     * @return  User login
     */
    public String getLogin() { return login; }

    /**
     * Getter for email field
     *
     * @return  User email
     */
    public String getEmail() { return email; }

    /**
     * Getter for birthday field
     *
     * @return  User birthday
     */
    public String getBirthday() { return birthday; }

    /**
     * Getter for password instance
     *
     * @return  instance of Password class
     */
    public Password getPassword() { return password; }

    /**
     * Getter for histories instances
     *
     * @return  list of instances of History class
     */
    public History[] getHistories() { return histories; }

    /**
     * Setter for last name field
     *
     * @param  last_name  User last name
     */
    public void setLast_name(String last_name) { this.last_name = last_name; }

    /**
     * Setter for first name field
     *
     * @param  first_name  User first name
     */
    public void setFirst_name(String first_name) { this.first_name = first_name; }

    /**
     * Setter for phone number field
     *
     * @param  phone_number  User phone number
     */
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }

    /**
     * Setter for email field
     *
     * @param  email  User email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Setter for birthday field
     *
     * @param  birthday  User birthday
     */
    public void setBirthday(String birthday) { this.birthday = birthday; }

    /**
     * Setter for histories instances
     *
     * @param  histories  list of instances of History class
     */
    public void setHistories(History[] histories) { this.histories = histories; }

    /**
     * Setter for unique identifier field
     *
     * @param  id  User id
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Setter for login field
     *
     * @param  login  User login
     */
    public void setLogin(String login) { this.login = login; }

    /**
     * Setter for password instance
     *
     * @param  password  instance of Password class
     */
    public void setPassword(Password password) { this.password = password; }

    /**
     * Outputting fields of User class in a string format
     *
     * @return  string field
     */
    @Override
    public String toString() {
        return "JsonUser{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", birthday=" + birthday +
                ", password=" + password +
                ", histories=" + Arrays.toString(histories) +
                '}';
    }
}
