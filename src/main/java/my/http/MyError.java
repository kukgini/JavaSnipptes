package my.http;

public class MyError {
    int code;
    String message;

    public MyError(Throwable t) {
        this.message = t.getMessage();
    }
}
