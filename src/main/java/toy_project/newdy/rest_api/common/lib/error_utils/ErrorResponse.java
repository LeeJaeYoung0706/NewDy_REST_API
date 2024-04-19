package toy_project.newdy.rest_api.common.lib.error_utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

@Component
public class ErrorResponse {
    /**
     * 에러 처리
     * @param code
     * @throws IOException
     */
    public void error(ErrorCode code) throws IOException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if(requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
            if (response != null){
                response.sendRedirect(request.getContextPath() + "/error/" + code.getValue());
            }
        }
    }
}
