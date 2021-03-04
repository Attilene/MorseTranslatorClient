package sample.utils.requests;

import sample.models.to.dict.DictEnter;
import sample.models.to.dict.DictRegistration;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class PostRequestUtil extends RequestsUtil implements Runnable {
    private Map<String, String> params;
    private final Thread thread;

    public PostRequestUtil(String url) { thread = new Thread(this, url); }

    @Override
    public void run() { System.out.println(send(thread.getName())); }

    @Override
    public String send(String url) {
        try {
            this.url = new URL(rootURL + url);
            HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches( false );
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            conn.setDoOutput(true);
            BufferedOutputStream out = new BufferedOutputStream(conn.getOutputStream());
            out.write(createRequestString().getBytes(StandardCharsets.UTF_8));
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
        PostRequestUtil postRequestUtil = new PostRequestUtil("/enter");
//        postRequestUtil.setParams(new DictRegistration().setParams(new ArrayList<>() {{
//            add("Sergey");
//            add("Tkachev");
//            add("yau20");
//            add("1e2jef@mail.ru");
//            add("567890876532");
//            add("2001-07-10");
//            add("qwerty");
//            add("12345");
//        }}));
        postRequestUtil.setParams(new DictEnter().setParams(new ArrayList<>() {{
            add("1e2jef@mail.ru");
            add("qwerty");
            add("12345");
        }}));
        postRequestUtil.thread.start();
    }
}
