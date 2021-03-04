package sample.utils.requests;

public class PutRequestUtil extends RequestsUtil implements Runnable {
    private final Thread thread;

    public PutRequestUtil(Thread thread) {
        this.thread = thread;
    }

    @Override
    public String send(String url) {
        return null;
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {

    }
}
