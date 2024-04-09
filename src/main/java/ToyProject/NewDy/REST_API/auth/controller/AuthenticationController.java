package ToyProject.NewDy.REST_API.auth.controller;


import ToyProject.NewDy.REST_API.auth.controller.swagger.AuthenticationControllerSwagger;
import ToyProject.NewDy.REST_API.common.lib.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController implements AuthenticationControllerSwagger {

    public Response<?> memberSave(){
        return Response.builder(200 , null).build();
    }
}
