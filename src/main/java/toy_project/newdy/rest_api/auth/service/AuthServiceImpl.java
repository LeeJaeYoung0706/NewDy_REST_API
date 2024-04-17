package toy_project.newdy.rest_api.auth.service;

import toy_project.newdy.rest_api.auth.domain.AuthMember;
import toy_project.newdy.rest_api.auth.dto.SignUpMemberDTO;
import toy_project.newdy.rest_api.auth.repository.AuthMemberRepository;
import toy_project.newdy.rest_api.common.lib.response.Response;
import toy_project.newdy.rest_api.common.service.AddressService;
import toy_project.newdy.rest_api.member.domain.Member;
import toy_project.newdy.rest_api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Qualifier("defaultMemberService")
    private final MemberService memberService;
    private final AuthMemberRepository authMemberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AddressService addressService;

    private String enCodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    @Transactional
    public Response<String> memberSave(SignUpMemberDTO signUpMemberDTO) {
        authMemberSave(signUpMemberDTO);
        Member member = memberService.memberSave(signUpMemberDTO);
        return Response.builder(200 , member.getSigninId()).build();
    }

    // 인증 객체 저장
    private void authMemberSave(SignUpMemberDTO signUpMemberDTO) {
        // 비밀번호 암호화
        String enCodePassword = enCodePassword(signUpMemberDTO.getPassword());
        // auth 객체 생성
        AuthMember userAuthMember = AuthMember.createUserAuthMember(signUpMemberDTO, enCodePassword);
        // auth 저장
        authMemberRepository.save(userAuthMember);
    }



}
