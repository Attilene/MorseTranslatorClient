package sample.models.json;

import java.io.Serializable;
import java.util.Date;

/**
 * History model class for creating json-entity
 *
 * @author  Artem Bakanov aka Attilene
 */
public class History implements Serializable {
    /**
     * Unique identifier of History record from database
     */
    private Long id;

    /**
     * Start string column
     */
    private String start_string;

    /**
     * End string column
     */
    private String end_string;

    /**
     * Operation time column
     */
    private Date operation_time;

    /**
     * Getter for start string field
     *
     * @return  History start string
     */
    public String getStart_string() { return start_string; }

    /**
     * Getter for end string field
     *
     * @return  History end string
     */
    public String getEnd_string() { return end_string; }

    /**
     * Getter for unique identifier field
     *
     * @return  History id
     */
    public Long getId() { return id; }

    /**
     * Getter for operation time field
     *
     * @return  History operation time
     */
    public Date getOperation_time() { return operation_time; }

    /**
     * Setter for operation time field
     *
     * @param  operation_time  History operation time
     */
    public void setOperation_time(Date operation_time) { this.operation_time = operation_time; }

    /**
     * Setter for start string field
     *
     * @param  start_string  History start string
     */
    public void setStart_string(String start_string) { this.start_string = start_string; }

    /**
     * Setter for end string field
     *
     * @param  end_string History end string
     */
    public void setEnd_string(String end_string) { this.end_string = end_string; }

    /**
     * Setter for unique identifier field
     *
     * @param  id  History id
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Outputting fields of History class in a string format
     *
     * @return  string field
     */
    @Override
    public String toString() {
        return "JsonHistory{" +
                "id=" + id +
                ", start_string='" + start_string + '\'' +
                ", end_string='" + end_string + '\'' +
                ", operation_time=" + operation_time +
                '}';
    }
}
