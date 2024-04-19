package toy_project.newdy.rest_api.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy_project.newdy.rest_api.member.controller.swagger.MemberControllerSwagger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController implements MemberControllerSwagger {

}
