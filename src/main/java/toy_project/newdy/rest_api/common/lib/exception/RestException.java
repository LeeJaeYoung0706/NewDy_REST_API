package toy_project.newdy.rest_api.common.lib.exception;


import lombok.Getter;
import toy_project.newdy.rest_api.common.lib.response.Response;


@Getter
public class RestException extends RuntimeException{

    private final int code;

    private RestException(String message , int code) {
        super(message);
        this.code = code;
    }

    public static Response<?> build(String message, int code) {
        return Response.builder(code, message).build();
    }

}
