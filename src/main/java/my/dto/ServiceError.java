package my.dto;

public class ServiceError {
    int code;
    String message;

    public ServiceError(Throwable t) {
        this.message = t.getMessage();
    }
}
