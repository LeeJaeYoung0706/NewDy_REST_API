package ToyProject.NewDy.REST_API.common.lib.exception;

public class NotEnoughSumPointException extends RuntimeException{

    public NotEnoughSumPointException() {
        super();
    }

    public NotEnoughSumPointException(String message) {
        super(message);
    }

    public NotEnoughSumPointException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughSumPointException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughSumPointException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
