package ToyProject.NewDy.REST_API.auth.controller;


import ToyProject.NewDy.REST_API.auth.swagger.AuthenticationControllerSwagger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController implements AuthenticationControllerSwagger {

    @GetMapping("/test")
    public String testRequest() {
        return "Success";
    }
}
