package toy_project.newdy.rest_api.member.service;

import toy_project.newdy.rest_api.auth.dto.SignUpMemberDTO;
import toy_project.newdy.rest_api.member.domain.Member;

public interface MemberService {

    Member findBySigninId(String signinId);
    Member memberSave(SignUpMemberDTO signUpMemberDTO);
}
