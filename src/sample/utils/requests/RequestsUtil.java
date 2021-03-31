package sample.utils.requests;

import javafx.stage.Stage;
import sample.utils.alerts.AlertsUtil;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Class for creating requests to the server
 *
 * @author  Artem Bakanov aka Attilene
 */
public class RequestsUtil implements Runnable {
    /**
     * Root url-path to which the request will be sent to the server
     */
    private static final String ROOT_URL = "http://localhost:22222";

    /**
     * Maximum waiting time for a connection to the server, in milliseconds
     */
    private static final int TIMEOUT = 10000;

    /**
     * Connection status with the server
     */
    private Boolean disconnect = false;

    /**
     * Parameters to be added to the request
     */
    private Map<String, String> params;

    /**
     * One of the next request methods: GET, POST, PUT, DELETE
     */
    private final String method;

    /**
     * Response from the server to the request
     */
    private String response;

    /**
     * Execution flow of request to the server
     */
    public Thread thread;

    /**
     * Relative url-path to which the request will be sent to the server
     */
    private URL url;

    /**
     * Constructor for RequestUtil class
     *
     * @param  url     relative url-path to which the request will be sent to the server
     * @param  method  one of the next request methods: GET, POST, PUT, DELETE
     */
    public RequestsUtil(String url, String method) {
        this.method = method;
        this.thread = new Thread(this, url);
    }

    /**
     * Method for starting a request execution flow to the server and receiving a response from the server
     */
    @Override
    public void run() { response = send(thread.getName()); }

    /**
     * Method for sending request to the server
     *
     * @param   url  relative url-path to which the request will be sent to the server
     * @return       response string from the server
     */
    public String send(String url) {
        try {
            this.url = new URL(ROOT_URL + url);
            HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod(method);
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

    /**
     * Reading input stream for getting response from the server
     *
     * @param   conn  HTTP connection with the server
     * @return        response string from the server
     */
    private static String readInputStream(HttpURLConnection conn) {
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

    /**
     * Adding parameters to request body
     *
     * @param   params  parameters to be added to the request
     * @return          prepared request with parameters to send to the server
     */
    private static String createRequestString(Map<String, String> params) {
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

    /**
     * Starting a thread waiting for a response from the server
     *
     * @param  requestsUtil  instance of RequestsUtil class
     * @param  stage         window where displaying alert
     */
    public static void runningThread(RequestsUtil requestsUtil, Stage stage) {
        long time = System.currentTimeMillis();
        while (!requestsUtil.thread.isInterrupted()) {
            if (requestsUtil.getDisconnect()) {
                AlertsUtil.showInternalServerErrorAlert(stage);
                requestsUtil.setDisconnect(false);
                break;
            }
            if (requestsUtil.getResponse() != null) break;
            if (System.currentTimeMillis() - time > TIMEOUT) requestsUtil.setDisconnect(true);
        }
    }

    /**
     * Getter for response from the server
     *
     * @return  response string
     */
    public String getResponse() { return response; }

    /**
     * Getter for connection status to the server
     *
     * @return  true, if connection is open, or false, if the opposite is true
     */
    public Boolean getDisconnect() { return disconnect; }

    /**
     * Getter for relative url-path
     *
     * @return  relative url-path
     */
    public URL getUrl() { return url; }

    /**
     * Setter for parameters of request method
     *
     * @param  params  parameters of request method
     */
    public void setParams(Map<String, String> params) { this.params = params; }

    /**
     * Setter for connection status with the server
     *
     * @param  disconnect  connection status
     */
    public void setDisconnect(Boolean disconnect) { this.disconnect = disconnect; }
}
