package toy_project.newdy.rest_api.member.service;

import toy_project.newdy.rest_api.auth.dto.SignUpMemberDTO;
import toy_project.newdy.rest_api.common.domain.Address;
import toy_project.newdy.rest_api.member.domain.Member;
import toy_project.newdy.rest_api.member.enums.PointKind;
import toy_project.newdy.rest_api.member.repository.MemberRepository;
import toy_project.newdy.rest_api.point.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier(value = "defaultMemberService")
@RequiredArgsConstructor
@Slf4j
public class DefaultMemberService extends MemberSaveTemplate implements MemberService {

    private final MemberRepository memberRepository;
    @Qualifier("fixedPointService")
    private final PointService pointService;

    @Override
    @Transactional(readOnly = true)
    public Member findBySigninId(String signinId) {
        return memberRepository.findBySigninId(signinId).orElseThrow(() -> new IllegalArgumentException("유저 정보를 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    public Member memberSave(SignUpMemberDTO signUpMemberDTO) {

        if (existSigninIdCheck(signUpMemberDTO.getSigninId()))
            throw new IllegalStateException("이미 존재하는 아이디입니다.");

        Member member = super.memberSave(signUpMemberDTO);
        Member save = memberRepository.save(member);
        //log.info("동일성 비교 = {}  , {}", member == save , member);
        return save;
    }

    /**
     * Member 저장시 변경될 가능성으로 인해 확장성 확보를 위한 Template 패턴
     * @param signUpMemberDTO
     * @param member
     */
    @Override
    @Transactional
    protected void changeMemberSaveDerivation(SignUpMemberDTO signUpMemberDTO, Member member) {
        Address.createAddress(signUpMemberDTO.getCity(), signUpMemberDTO.getStreet(), signUpMemberDTO.getZipCode(), member);
        pointService.addPoint(member, 500 , PointKind.SIGN_UP);
    }

    @Transactional(readOnly = true)
    public boolean existSigninIdCheck(String signinId){
        return memberRepository.existsBySigninId(signinId);
    }

}