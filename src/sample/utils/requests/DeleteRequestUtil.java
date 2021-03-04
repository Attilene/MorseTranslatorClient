package sample.utils.requests;

public class DeleteRequestUtil extends RequestsUtil implements Runnable {
    private final Thread thread;

    public DeleteRequestUtil(Thread thread) {
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
