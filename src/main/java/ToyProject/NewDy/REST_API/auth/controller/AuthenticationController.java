package ToyProject.NewDy.REST_API.auth.controller;


import ToyProject.NewDy.REST_API.auth.controller.swagger.AuthenticationControllerSwagger;
import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.auth.service.AuthService;
import ToyProject.NewDy.REST_API.common.lib.response.Response;
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
    public Response<String> memberSave(@RequestBody @Valid SignUpMemberDTO signUpMemberDTO, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors())
            throw new BindException(bindingResult);
        return authService.memberSave(signUpMemberDTO);
    }
}
