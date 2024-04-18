package toy_project.newdy.rest_api.member.service;

import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.member.domain.Member;

public interface MemberService {

    Member findBySigninId(String signinId);
    Member memberSave(SignUpMemberRequestDTO signUpMemberDTO);
}
