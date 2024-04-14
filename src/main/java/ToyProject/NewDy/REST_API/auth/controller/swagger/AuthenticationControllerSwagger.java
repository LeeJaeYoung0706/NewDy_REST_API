package ToyProject.NewDy.REST_API.auth.controller.swagger;

import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.common.lib.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "AuthenticationController", description = "인증 컨트롤러")
public interface AuthenticationControllerSwagger {

    Response<?> memberSave(SignUpMemberDTO signUpMemberDTO, BindingResult bindingResult) throws BindException;
}
