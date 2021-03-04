package sample.utils.requests;

import sample.models.json.JsonUser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequestUtil extends RequestsUtil implements Runnable {
    private final Thread thread;

    public GetRequestUtil(String url) {
        thread = new Thread(this, url);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(gson.fromJson(send(thread.getName()), JsonUser.class));
    }

    @Override
    public String send(String url) {
        try {
            this.url = new URL(rootURL + url);
            HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            return readInputStream(conn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        GetRequestUtil getRequestUtil = new GetRequestUtil("/users/4");
    }
}
