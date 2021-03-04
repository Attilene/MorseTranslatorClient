package sample.utils.requests;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class RequestsUtil {
    protected static final String rootURL = "http://localhost:22222";
    protected static final int TIMEOUT = 10000;
    protected static final Gson gson = new Gson();
    protected URL url;

    protected String readInputStream(HttpURLConnection conn) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            conn.disconnect();
        }
    }

    public abstract String send(String url);
}
