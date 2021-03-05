package sample.utils.requests;

import sample.models.to.dict.DictUpdateUser;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PutRequestUtil extends RequestsUtil {
    public PutRequestUtil(String url) { this.thread = new Thread(this, url); }

    @Override
    public void run() { System.out.println(send(thread.getName())); }

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
            BufferedOutputStream out = new BufferedOutputStream(conn.getOutputStream());
            out.write(createRequestString(params).getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
            return readInputStream(conn);
        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    public static void main(String[] args) {
        PutRequestUtil putRequestUtil = new PutRequestUtil("/update");
        putRequestUtil.setParams(new DictUpdateUser().setParams(new ArrayList<>() {{
            add("9");
            add("Sergey");
            add("Tkachev");
            add("yau20");
            add("1e2jef@mail.ru");
            add("87654352324");
            add("2001-10-10");
            add("zxcvbn");
            add("12345");
        }}));
        putRequestUtil.thread.start();
    }
}
