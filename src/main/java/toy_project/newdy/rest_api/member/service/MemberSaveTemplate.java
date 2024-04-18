package toy_project.newdy.rest_api.member.service;


import lombok.Builder;
import lombok.Getter;
import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.member.domain.Member;
import org.springframework.stereotype.Component;
import toy_project.newdy.rest_api.member.domain.MemberSaveTransferCreateBuilder;

import java.util.Date;
import java.util.Optional;

/**
 * Member 저장시 변경될 가능성으로 인해 확장성 확보를 위한 Template 패턴
 */
public abstract class MemberSaveTemplate{

    public Member memberSave(SignUpMemberRequestDTO signUpMemberDTO) {
//        Member member = Member.createMember(signUpMemberDTO);
        Member member = memberCreateBranch(signUpMemberDTO.getSigninId(), Optional.ofNullable(signUpMemberDTO.getBirth()));
        changeMemberSaveDerivation(signUpMemberDTO, member);
        return member;
    }

    private Member memberCreateBranch(String signinId, Optional<Date> optionalBirth) {
        return MemberSaveTransferCreateBuilder
                .builder()
                .signinId(signinId)
                .birth(optionalBirth)
                .build();
    }

    protected abstract void changeMemberSaveDerivation(SignUpMemberRequestDTO signUpMemberDTO, Member member);
}
