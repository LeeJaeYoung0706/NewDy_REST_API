package ToyProject.NewDy.REST_API.member.service;


import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.member.domain.Member;
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
