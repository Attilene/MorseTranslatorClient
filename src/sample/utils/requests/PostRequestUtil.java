package sample.utils.requests;

import sample.models.json.JsonUser;
import sample.models.to.dict.DictUser;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class PostRequestUtil extends RequestsUtil implements Runnable {
    private Map<String, String> params;
    private Thread thread;

    public PostRequestUtil(String url) {
        thread = new Thread(this, url);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(send("/users"));
    }

    @Override
    public String send(String url) {
        try {
            this.url = new URL(rootURL + url);
            HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            BufferedOutputStream out = new BufferedOutputStream(conn.getOutputStream());
            // TODO: Тесты
            JsonUser jsonUser = new JsonUser();
            jsonUser.setFirst_name("Derbin");
            jsonUser.setLast_name("Dmitriy");
            jsonUser.setLogin("t1mon");
            jsonUser.setEmail("97865hyasdsfgf@mail.ru");
            jsonUser.setPhone_number("889765432456");
            jsonUser.setBirthday("2001-07-10");
            out.write(gson.toJson(jsonUser).getBytes(StandardCharsets.UTF_8));
            //
            out.flush();
            out.close();
            return readInputStream(conn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setParams(Map<String, String> params) { this.params = params; }

    private String createRequestString() {
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

    public static void main(String[] args) {
        PostRequestUtil postRequestUtil = new PostRequestUtil("/users");
//        postRequestUtil.setParams(new DictUser().setParams(new ArrayList<>() {{
//            add("Sergey");
//            add("Tkachev");
//            add("yau20");
//            add("1e2jef@mail.ru");
//            add("89253136350");
//            add("2001-07-10");
//        }}));
    }
}
