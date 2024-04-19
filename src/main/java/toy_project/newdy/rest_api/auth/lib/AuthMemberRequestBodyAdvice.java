package toy_project.newdy.rest_api.auth.lib;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.auth.service.NickCheckNameService;
import toy_project.newdy.rest_api.common.lib.exception.NickNameVilificationException;

import java.io.IOException;
import java.lang.reflect.Type;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class AuthMemberRequestBodyAdvice implements RequestBodyAdvice {

    private final NickCheckNameService nickCheckNameService;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getParameterType() == SignUpMemberRequestDTO.class;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (body instanceof SignUpMemberRequestDTO) {
            String nickName = ((SignUpMemberRequestDTO) body).getNickName();
            if (nickCheckNameService.checkNickName(nickName))
                throw new NickNameVilificationException("적합하지 않은 닉네임 입니다.");
        }
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
