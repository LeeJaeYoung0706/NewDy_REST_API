package toy_project.newdy.rest_api.common.lib.exception;

import lombok.extern.slf4j.Slf4j;
import toy_project.newdy.rest_api.common.lib.response.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionAdvisor {

    @ExceptionHandler(RestException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> restException(RestException restException){
        log.error(restException.getMessage());
        return RestException.build(restException.getMessage(), ErrorCode.UNAUTHORIZED.getValue());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> userNotFoundException(UsernameNotFoundException usernameNotFoundException){
        log.error(usernameNotFoundException.getMessage());
        return RestException.build(usernameNotFoundException.getMessage(), ErrorCode.NOT_FOUND_USER.getValue());
    }

    @ExceptionHandler(BindException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> validationBindException(BindException bindException){
        log.error(bindException.getMessage());
        return RestException.build(bindException.getMessage(), ErrorCode.BINDING.getValue());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> validationIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        log.error(illegalArgumentException.getMessage());
        return RestException.build(illegalArgumentException.getMessage(), ErrorCode.ARGUMENT.getValue());
    }

    //NickNameVilificationException
    @ExceptionHandler(IllegalStateException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> validationIllegalStateException(IllegalStateException illegalStateException){
        log.error(illegalStateException.getMessage());
        return RestException.build(illegalStateException.getMessage(), ErrorCode.STATE.getValue());
    }

    @ExceptionHandler(NickNameVilificationException.class)
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> validationNickNameVilificationException(NickNameVilificationException nickNameVilificationException){
        log.error(nickNameVilificationException.getMessage());
        return RestException.build(nickNameVilificationException.getMessage(), ErrorCode.BINDING.getValue());
    }
}
