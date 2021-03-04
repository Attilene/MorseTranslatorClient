package sample.models.json;

import java.io.Serializable;
import java.util.Arrays;

public class JsonUser implements Serializable {
    public Long id;
    public String first_name;
    public String last_name;
    public String login;
    public String email;
    public String phone_number;
    public String birthday;
    public JsonPassword password;
    public JsonHistory[] histories;

    public Long getId() { return id; }

    public String getFirst_name() { return first_name; }

    public String getLast_name() { return last_name; }

    public String getPhone_number() { return phone_number; }

    public String getLogin() { return login; }

    public String getEmail() { return email; }

    public String getBirthday() { return birthday; }

    public JsonPassword getPassword() { return password; }

    public JsonHistory[] getHistories() { return histories; }

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
