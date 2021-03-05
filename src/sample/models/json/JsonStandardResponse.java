package sample.models.json;

import java.io.Serializable;

public class JsonStandardResponse implements Serializable {
    private String response;

    public String getResponse() { return response; }

    public void setResponse(String response) { this.response = response; }

    @Override
    public String toString() {
        return "JsonStandardResponse{" +
                "response='" + response + '\'' +
                '}';
    }
}
