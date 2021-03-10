package sample.utils.requests;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PutRequestUtil extends RequestsUtil {
    public PutRequestUtil(String url) { super(url); }

    @Override
    public String send(String url) {
        try {
            this.url = new URL(rootURL + url);
            HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod("PUT");
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
}
