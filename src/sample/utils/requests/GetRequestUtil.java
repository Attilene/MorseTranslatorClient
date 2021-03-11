package sample.utils.requests;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequestUtil extends RequestsUtil {
    public GetRequestUtil(String url) { super(url, "GET"); }

    @Override
    public String send(String url) {
        try {
            this.url = new URL(rootURL + url);
            HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("charset", "utf-8");
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            conn.connect();
            return readInputStream(conn);
        } catch (ConnectException e) {
            setDisconnect(true);
            return null;
        } catch (IOException e) { return null; }
    }
}
