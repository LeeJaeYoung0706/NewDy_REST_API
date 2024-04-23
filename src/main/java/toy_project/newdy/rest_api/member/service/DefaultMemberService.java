package toy_project.newdy.rest_api.member.service;

import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.common.domain.Address;
import toy_project.newdy.rest_api.common.lib.error_utils.ErrorCode;
import toy_project.newdy.rest_api.common.lib.exception.RestException;
import toy_project.newdy.rest_api.member.domain.Member;
import toy_project.newdy.rest_api.member.domain.MemberSaveTransferCreateBuilder;
import toy_project.newdy.rest_api.member.enums.PointKind;
import toy_project.newdy.rest_api.member.repository.MemberRepository;
import toy_project.newdy.rest_api.point.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

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

    //region 멤버 저장 메소드
    /**
     * 멤버 저장
     * @param signUpMemberDTO
     * @return
     * @throws RestException
     */
    @Override
    @Transactional
    public Member memberSave(SignUpMemberRequestDTO signUpMemberDTO) throws RestException {

        if (existSigninIdCheck(signUpMemberDTO.getSigninId()))
            throw new IllegalStateException("이미 존재하는 아이디입니다.");

        if (existNicknameCheck(signUpMemberDTO.getNickName()))
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");

        Member member = super.memberSave(signUpMemberDTO);
        Member saveMember = memberRepository.save(member);

        if (Objects.isNull(saveMember))
            throw new RestException("알수 없는 서버 오류" , ErrorCode.UNKNOWN.getValue());

        return saveMember;
    }

    /**
     * 로그인 아이디 중복 체크
     * @param signinId
     * @return
     */
    @Transactional(readOnly = true)
    public boolean existSigninIdCheck(String signinId){
        return memberRepository.existsBySigninId(signinId);
    }

    /**
     * 닉네임 중복 체크
     * @param nickName
     * @return
     */
    @Transactional(readOnly = true)
    public boolean existNicknameCheck(String nickName){
        return memberRepository.existsByNickName(nickName);
    }


    /**
     * Member 저장시 변경될 가능성으로 인해 확장성 확보를 위한 Template 패턴
     * @param signUpMemberDTO
     * @param member
     */
    @Override
    @Transactional
    protected void changeMemberSaveDerivation(SignUpMemberRequestDTO signUpMemberDTO, Member member) {
        Address.createAddress(signUpMemberDTO.getCity(), signUpMemberDTO.getStreet(), signUpMemberDTO.getZipCode(), member);
        pointService.addPoint(member, 500 , PointKind.SIGN_UP);
    }

    /**
     * DTO -> Member 변경 builder 사용 메소드
     * @param signUpMemberDTO
     * @return
     */
    @Override
    protected Member memberSaveTransfer(SignUpMemberRequestDTO signUpMemberDTO) {
        return MemberSaveTransferCreateBuilder
                .builder()
                .signinId(signUpMemberDTO.getSigninId())
                .birth(Optional.ofNullable(signUpMemberDTO.getBirth()))
                .nickName(signUpMemberDTO.getNickName())
                .build();
    }
    //endregion
}
