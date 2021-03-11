package sample.models.json;

import java.io.Serializable;

public class Password implements Serializable {
    private Long id;
    private String hash;
    private String salt;

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
