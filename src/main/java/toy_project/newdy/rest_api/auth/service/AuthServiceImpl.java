package toy_project.newdy.rest_api.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import toy_project.newdy.rest_api.auth.domain.AuthMember;
import toy_project.newdy.rest_api.auth.dto.SignInMemberRequestDTO;
import toy_project.newdy.rest_api.auth.dto.SignInMemberResponseDTO;
import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.auth.lib.BcCryptoUtils;
import toy_project.newdy.rest_api.auth.lib.JwtTokenProvider;
import toy_project.newdy.rest_api.auth.lib.SecurityMemberDetails;
import toy_project.newdy.rest_api.auth.repository.AuthMemberRepository;
import toy_project.newdy.rest_api.common.lib.response.Response;
import toy_project.newdy.rest_api.member.domain.Member;
import toy_project.newdy.rest_api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Qualifier("defaultMemberService")
    private final MemberService memberService;
    private final AuthMemberRepository authMemberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BcCryptoUtils bcCryptoUtils;

    //region 멤버 저장 메소드 ( member Service 와 엮여 있습니다 )
    /**
     * Member 저장
     * @param signUpMemberDTO
     * @return
     */
    @Override
    @Transactional
    public Response<String> memberSave(SignUpMemberRequestDTO signUpMemberDTO) {
        authMemberSave(signUpMemberDTO);
        Member member = memberService.memberSave(signUpMemberDTO);
        return Response.builder(200 , member.getSigninId()).build();
    }

    // 인증 객체 저장
    private void authMemberSave(SignUpMemberRequestDTO signUpMemberDTO) {
        // 비밀번호 암호화
        String enCodePassword = bcCryptoUtils.enCodePassword(signUpMemberDTO.getPassword());
        // auth 객체 생성
        AuthMember userAuthMember = AuthMember.createUserAuthMember(signUpMemberDTO, enCodePassword);
        // auth 저장
        authMemberRepository.save(userAuthMember);
    }
    //endregion

    //region UserDetailsService loadUserByUsername 구현
    /**
     * Security UserDetailsService를 상속받았기 때문에 구현
     * @param signinId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String signinId) throws UsernameNotFoundException {
        return authMemberRepository.findBySigninId(signinId)
                .map( authMember -> SecurityMemberDetails.createDetails(authMember))
                .orElseThrow(() -> new UsernameNotFoundException("유저 정보를 찾을 수 없습니다."));
    }
    //endregion

    //region 멤버 로그인 signinMember
    @Override
    @Transactional
    public Response<SignInMemberResponseDTO> signinMember(SignInMemberRequestDTO signInMemberRequestDTO) throws UsernameNotFoundException {
        SignInMemberResponseDTO signInMemberResponseDTO = authMemberRepository.findBySigninId(signInMemberRequestDTO.getSigninId())
                .filter(authMember -> bcCryptoUtils.matchPassword(signInMemberRequestDTO.getPassword(), authMember.getPassword()))
                .map(authMember -> SignInMemberResponseDTO.createSignInMemberResponseDTO(
                        authMember.getSigninId(),
                        jwtTokenProvider.generateToken(authMember.getSigninId(), authMember.getAuthRole().getRole()),
                        memberService.findBySigninId(authMember.getSigninId()).getNickName(),
                        getMenuGrantList(authMember),
                        // 앞단에서 필요하다면 react의 경우 토큰을 푸는 것이 알맞지 않으니까 넣어주는게 맞을지.
                        authMember.getAuthRole().getRole()
                )).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
        return Response.builder(200 , signInMemberResponseDTO).build();
    }

    //권한 가져오기
    private List<String> getMenuGrantList(AuthMember authMember){
        // Menu 권한 추가
        List<String> grantList = Arrays.stream(authMember.getAuthRole().getGrantOptionList())
                .map(authMemberOption -> authMemberOption.name()).collect(Collectors.toList());
        if (grantList.isEmpty())
            throw new IllegalStateException("알맞지 않은 회원입니다. 관리자에게 문의하세요.");
        return grantList;
    }
    //endregion

    @Override
    public SecurityMemberDetails findSecurityMemberDetailsBySigninId(String signinId) throws UsernameNotFoundException {
        return authMemberRepository.findBySigninId(signinId)
                .map( authMember ->  SecurityMemberDetails.createDetails(authMember))
                .orElseThrow( () -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

}
