package sample.models.json;

import java.io.Serializable;
import java.util.Date;

public class JsonHistory implements Serializable {
    public Long id;
    public String start_string;
    public String end_string;
    public Date operation_time;

    public String getStart_string() { return start_string; }

    public String getEnd_string() { return end_string; }

    public Long getId() { return id; }

    public Date getOperation_time() { return operation_time; }

    public void setOperation_time(Date operation_time) { this.operation_time = operation_time; }

    public void setStart_string(String start_string) { this.start_string = start_string; }

    public void setEnd_string(String end_string) { this.end_string = end_string; }

    public void setId(Long id) { this.id = id; }

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
