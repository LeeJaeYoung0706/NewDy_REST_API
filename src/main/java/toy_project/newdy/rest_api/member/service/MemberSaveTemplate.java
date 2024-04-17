package toy_project.newdy.rest_api.member.service;


import toy_project.newdy.rest_api.auth.dto.SignUpMemberDTO;
import toy_project.newdy.rest_api.member.domain.Member;
import org.springframework.stereotype.Component;

/**
 * Member 저장시 변경될 가능성으로 인해 확장성 확보를 위한 Template 패턴
 */
@Component
public abstract class MemberSaveTemplate{

    public Member memberSave(SignUpMemberDTO signUpMemberDTO) {
        Member member = Member.createMember(signUpMemberDTO);
        changeMemberSaveDerivation(signUpMemberDTO, member);
        return member;
    }

    protected abstract void changeMemberSaveDerivation(SignUpMemberDTO signUpMemberDTO, Member member);
}
