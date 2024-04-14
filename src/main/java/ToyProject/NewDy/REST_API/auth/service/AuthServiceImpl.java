package ToyProject.NewDy.REST_API.auth.service;

import ToyProject.NewDy.REST_API.auth.domain.AuthMember;
import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.auth.repository.AuthMemberRepository;
import ToyProject.NewDy.REST_API.common.domain.Address;
import ToyProject.NewDy.REST_API.common.lib.response.Response;
import ToyProject.NewDy.REST_API.common.repository.AddressRepository;
import ToyProject.NewDy.REST_API.common.service.AddressService;
import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
