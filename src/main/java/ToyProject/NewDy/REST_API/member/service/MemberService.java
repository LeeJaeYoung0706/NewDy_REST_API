package ToyProject.NewDy.REST_API.member.service;

import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.member.domain.Member;

public interface MemberService {

    Member findBySigninId(String signinId);
    Member memberSave(SignUpMemberDTO signUpMemberDTO);
}
