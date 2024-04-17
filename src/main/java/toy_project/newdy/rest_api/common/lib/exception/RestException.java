package toy_project.newdy.rest_api.common.lib.exception;


import lombok.Getter;


@Getter
public class RestException extends RuntimeException{

    private final int code;

    private RestException(String message , int code) {
        super(message);
        this.code = code;
    }

    public static RestException build(String message, int code) {
        return new RestException(message , code);
    }

}
