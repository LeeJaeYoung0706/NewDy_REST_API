package ToyProject.NewDy.REST_API.auth.controller;


import ToyProject.NewDy.REST_API.auth.controller.swagger.AuthenticationControllerSwagger;
import ToyProject.NewDy.REST_API.auth.service.AuthService;
import ToyProject.NewDy.REST_API.common.lib.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationControllerSwagger {

    private final AuthService authService;


    public Response<?> memberSave(){
        return Response.builder(200 , null).build();
    }
}
