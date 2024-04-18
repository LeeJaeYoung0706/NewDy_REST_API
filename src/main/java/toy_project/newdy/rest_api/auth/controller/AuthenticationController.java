package toy_project.newdy.rest_api.auth.controller;


import toy_project.newdy.rest_api.auth.controller.swagger.AuthenticationControllerSwagger;
import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.auth.service.AuthService;
import toy_project.newdy.rest_api.common.lib.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationControllerSwagger {

    private final AuthService authService;

    @PostMapping("/signup")
    public Response<String> memberSave(@RequestBody @Valid SignUpMemberRequestDTO signUpMemberDTO, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors())
            throw new BindException(bindingResult);
        return authService.memberSave(signUpMemberDTO);
    }
}
