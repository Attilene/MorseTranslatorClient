package sample.utils.requests;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public abstract class RequestsUtil implements Runnable {
    protected static final String rootURL = "http://localhost:22222";
    protected static final Gson gson = new Gson();
    protected static final int TIMEOUT = 10000;
    protected Map<String, String> params;
    protected Thread thread;
    protected URL url;

    public abstract String send(String url);

    public void setParams(Map<String, String> params) { this.params = params; }

    protected static String readInputStream(HttpURLConnection conn) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            conn.disconnect();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            conn.disconnect();
            return null;
        }
    }

    protected static String createRequestString(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        params.forEach((name, value) -> {
            result.append(name);
            result.append('=');
            result.append(value);
            result.append('&');
        });
        String resultString = result.toString();
        return !resultString.isEmpty()
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
