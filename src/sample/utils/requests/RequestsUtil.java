package sample.utils.requests;

import javafx.stage.Stage;
import sample.utils.AlertsUtil;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RequestsUtil implements Runnable {
    protected static final String rootURL = "http://localhost:22222";
    protected Boolean disconnect = false;
    protected static final int TIMEOUT = 5000;
    protected Map<String, String> params;
    protected String response;
    public Thread thread;
    protected URL url;

    @Override
    public void run() { response = send(thread.getName()); }

    public RequestsUtil(String url) { this.thread = new Thread(this, url); }

    public String send(String url) {
        try {
            this.url = new URL(rootURL + url);
            HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            conn.setDoOutput(true);
            conn.connect();
            BufferedOutputStream out = new BufferedOutputStream(conn.getOutputStream());
            out.write(createRequestString(params).getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
            return readInputStream(conn);
        } catch (ConnectException e) {
            setDisconnect(true);
            return null;
        } catch (IOException e) { return null; }
    }

    public void setParams(Map<String, String> params) { this.params = params; }

    public String getResponse() { return response; }

    public Boolean getDisconnect() { return disconnect; }

    public static int getTIMEOUT() { return TIMEOUT; }

    public URL getUrl() { return url; }

    public static String getRootURL() { return rootURL; }

    public void setDisconnect(Boolean disconnect) { this.disconnect = disconnect; }

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

    public static void runningThread(RequestsUtil requestsUtil, Stage stage) {
        while (!requestsUtil.thread.isInterrupted()) {
            if (requestsUtil.getDisconnect()) {
                AlertsUtil.showInternalServerErrorAlert(stage);
                requestsUtil.setDisconnect(false);
                break;
            }
            if (requestsUtil.getResponse() != null) break;
        }
    }
}
