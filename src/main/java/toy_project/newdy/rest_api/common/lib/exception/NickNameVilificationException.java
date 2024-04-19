package toy_project.newdy.rest_api.common.lib.exception;

public class NickNameVilificationException extends RuntimeException{

    public NickNameVilificationException() {
        super();
    }

    public NickNameVilificationException(String message) {
        super(message);
    }

    public NickNameVilificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NickNameVilificationException(Throwable cause) {
        super(cause);
    }

    protected NickNameVilificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
