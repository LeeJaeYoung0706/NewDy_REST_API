package ToyProject.NewDy.REST_API.auth.service;

import ToyProject.NewDy.REST_API.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Qualifier("defaultMemberService")
    private final MemberService memberService;
}
