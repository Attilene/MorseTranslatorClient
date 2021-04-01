package sample.models.json;

import java.io.Serializable;

/**
 * Password model class for creating json-entity
 *
 * @author  Artem Bakanov aka Attilene
 */
public class Password implements Serializable {
    /**
     * Unique identifier of Password record from database
     */
    private Long id;

    /**
     * Hash column
     */
    private String hash;

    /**
     * Salt column
     */
    private String salt;

    /**
     * Getter for unique identifier field
     *
     * @return  Password id
     */
    public Long getId() { return id; }

    /**
     * Getter for hash field
     *
     * @return  Password field
     */
    public String getHash() { return hash; }

    /**
     * Getter for start string field
     *
     * @return  History start string
     */
    public String getSalt() { return salt; }

    /**
     * Setter for salt field
     *
     * @param  salt  Password salt
     */
    public void setSalt(String salt) { this.salt = salt; }

    /**
     * Setter for hash field
     *
     * @param  hash  Password hash
     */
    public void setHash(String hash) { this.hash = hash; }

    /**
     * Setter for unique identifier
     *
     * @param  id  Password id
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Outputting fields of History class in a string format
     *
     * @return  string field
     */
    @Override
    public String toString() {
        return "JsonPassword{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
