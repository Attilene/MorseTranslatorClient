package sample.utils.requests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequestUtil<T> extends RequestsUtil {
    protected Class<T> typeClass;

    public GetRequestUtil(String url, Class<T> typeClass) {
        this.typeClass = typeClass;
        thread = new Thread(this, url);
    }

    @Override
    public void run() { System.out.println(gson.fromJson(send(thread.getName()), typeClass)); }

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
            return readInputStream(conn);
        } catch (IOException e) { return null; }
    }
}
