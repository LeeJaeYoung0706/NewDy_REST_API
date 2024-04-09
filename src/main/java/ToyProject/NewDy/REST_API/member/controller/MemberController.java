package ToyProject.NewDy.REST_API.member.controller;


import ToyProject.NewDy.REST_API.common.lib.response.Response;
import ToyProject.NewDy.REST_API.member.controller.swagger.MemberControllerSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController implements MemberControllerSwagger {


    @Override
    public Response<?> memberSave() {
        return null;
    }
}
