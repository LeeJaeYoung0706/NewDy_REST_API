package toy_project.newdy.rest_api.common.lib.exception;

import toy_project.newdy.rest_api.common.lib.response.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionAdvisor {

    @ExceptionHandler(RestException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> restException(RestException e){
        RestException exception = RestException.build(e.getMessage(), ErrorCode.UNAUTHORIZED.getValue());
        return Response.builder( exception.getCode(), null ).message(exception.getMessage()).build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> userNotFoundException(UsernameNotFoundException usernameNotFoundException){
        RestException exception = RestException.build(usernameNotFoundException.getMessage(), ErrorCode.NOT_FOUND_USER.getValue());
        return Response.builder( exception.getCode(), null ).message(exception.getMessage()).build();
    }

    @ExceptionHandler(BindException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> validationBindException(BindException bindException){
        RestException exception = RestException.build(bindException.getMessage(), ErrorCode.BINDING.getValue());
        return Response.builder( exception.getCode(), null ).message(exception.getMessage()).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> validationIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        RestException exception = RestException.build(illegalArgumentException.getMessage(), ErrorCode.ARGUMENT.getValue());
        return Response.builder( exception.getCode(), null ).message(exception.getMessage()).build();
    }

    @ExceptionHandler(IllegalStateException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> validationIllegalStateException(IllegalStateException illegalStateException){
        RestException exception = RestException.build(illegalStateException.getMessage(), ErrorCode.STATE.getValue());
        return Response.builder( exception.getCode(), null ).message(exception.getMessage()).build();
    }
}
