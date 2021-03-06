package sample.utils.requests;

import sample.models.to.dict.DictEnter;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PostRequestUtil extends RequestsUtil {
    public PostRequestUtil(String url) { thread = new Thread(this, url); }

    @Override
    public void run() { response = send(thread.getName()); }

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
        } catch (ConnectException e) {
            disconnect = true;
            return null;
        } catch (IOException e) { return null; }
    }

    public static void main(String[] args) {
        PostRequestUtil postRequestUtil = new PostRequestUtil("/enter");
        postRequestUtil.setParams(new DictEnter().setParams(new ArrayList<>() {{
            add("t1mon@gmail.com");
            add("tyhjns3gddf");
            add("iouioytry");
        }}));
        postRequestUtil.thread.start();
    }
}
