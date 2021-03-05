package sample.models.json;

import java.io.Serializable;
import java.util.Arrays;

public class JsonUser implements Serializable {
    private Long id;
    private String first_name;
    private String last_name;
    private String login;
    private String email;
    private String phone_number;
    private String birthday;
    private JsonPassword password;
    private JsonHistory[] histories;

    public Long getId() { return id; }

    public String getFirst_name() { return first_name; }

    public String getLast_name() { return last_name; }

    public String getPhone_number() { return phone_number; }

    public String getLogin() { return login; }

    public String getEmail() { return email; }

    public String getBirthday() { return birthday; }

    public JsonPassword getPassword() { return password; }

    public JsonHistory[] getHistories() { return histories; }

    public void setLast_name(String last_name) { this.last_name = last_name; }

    public void setFirst_name(String first_name) { this.first_name = first_name; }

    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }

    public void setEmail(String email) { this.email = email; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    public void setHistories(JsonHistory[] histories) { this.histories = histories; }

    public void setId(Long id) { this.id = id; }

    public void setLogin(String login) { this.login = login; }

    public void setPassword(JsonPassword password) { this.password = password; }

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
