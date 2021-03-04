package sample.models.json;

import java.io.Serializable;

public class JsonPassword implements Serializable {
    public Long id;
    public String hash;
    public String salt;

    public Long getId() { return id; }

    public String getHash() { return hash; }

    public String getSalt() { return salt; }

    public void setSalt(String salt) { this.salt = salt; }

    public void setHash(String hash) { this.hash = hash; }

    public void setId(Long id) { this.id = id; }

    @Override
    public String toString() {
        return "JsonPassword{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
