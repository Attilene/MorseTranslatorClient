package sample.utils.requests;

import sample.models.json.JsonUser;

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

    public static void main(String[] args) {
        GetRequestUtil<JsonUser> getRequestUtil = new GetRequestUtil<>("/users", JsonUser.class);
        getRequestUtil.thread.start();
//        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println(gson1.fromJson("{\"id\":13,\"first_name\":\"Dmitriy\",\"last_name\":\"Derbin\",\"login\":\"t1mon\",\"email\":\"t1mon@gmail.com\",\"phone_number\":\"1234567890\",\"birthday\":\"2001-03-15\",\"password\":{\"id\":13,\"hash\":\"tyhjns3gddf\",\"salt\":\"iouioytry\"},\"histories\":[]}", JsonUser.class));
    }
}
