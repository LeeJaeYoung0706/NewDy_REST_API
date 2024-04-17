package toy_project.newdy.rest_api.member.controller;


import toy_project.newdy.rest_api.common.lib.response.Response;
import toy_project.newdy.rest_api.member.controller.swagger.MemberControllerSwagger;
import lombok.RequiredArgsConstructor;
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
