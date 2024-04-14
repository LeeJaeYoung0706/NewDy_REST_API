package ToyProject.NewDy.REST_API.member.service;

import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.common.domain.Address;
import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.member.enums.PointKind;
import ToyProject.NewDy.REST_API.member.repository.MemberRepository;
import ToyProject.NewDy.REST_API.point.service.PointService;
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

}
