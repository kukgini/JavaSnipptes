package my.http;

public class JsonError {
    int code;
    String message;

    public JsonError (Throwable t) {
        this.message = t.getMessage();
    }
}
