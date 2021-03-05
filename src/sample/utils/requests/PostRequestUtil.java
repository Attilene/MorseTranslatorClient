package sample.utils.requests;

import sample.models.to.dict.DictEnter;
import sample.models.to.dict.DictRegistration;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PostRequestUtil extends RequestsUtil {
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
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            conn.setDoOutput(true);
            BufferedOutputStream out = new BufferedOutputStream(conn.getOutputStream());
            out.write(createRequestString(params).getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
            return readInputStream(conn);
        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    public static void main(String[] args) {
        PostRequestUtil postRequestUtil = new PostRequestUtil("/registration");
        postRequestUtil.setParams(new DictRegistration().setParams(new ArrayList<>() {{
            add("Dmitriy");
            add("Derbin");
            add("t1mon");
            add("t1mon@gmail.com");
            add("1234567890");
            add("2001-03-15");
            add("tyhjns3gddf");
            add("iouioytry");
        }}));
//        postRequestUtil.setParams(new DictEnter().setParams(new ArrayList<>() {{
//            add("t1mon@gmail.com");
//            add("tyhjns3gddf");
//            add("iouioytry");
//        }}));
        postRequestUtil.thread.start();
    }
}
