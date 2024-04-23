package toy_project.newdy.rest_api.auth.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import toy_project.newdy.rest_api.auth.controller.swagger.AuthenticationControllerSwagger;
import toy_project.newdy.rest_api.auth.dto.SignInMemberRequestDTO;
import toy_project.newdy.rest_api.auth.dto.SignInMemberResponseDTO;
import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.auth.service.AuthService;
import toy_project.newdy.rest_api.common.lib.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy_project.newdy.rest_api.member.service.MemberService;

import static toy_project.newdy.rest_api.common.lib.RegexList.EMAIL_REGEX;
import static toy_project.newdy.rest_api.common.lib.RegexList.NICKNAME_REGEX;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController implements AuthenticationControllerSwagger {

    private final AuthService authService;
    @Qualifier(value = "defaultMemberService")
    private final MemberService memberService;

    @PostMapping("/signup")
    @Override
    public Response<String> memberSave(@RequestBody @Valid SignUpMemberRequestDTO signUpMemberDTO, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors())
            throw new BindException(bindingResult);
        return authService.memberSave(signUpMemberDTO);
    }

    @GetMapping("/signinIdCheck")
    @Override
    public Response<Boolean> existsBySigninId(@RequestParam(value = "signinId") String signinId) throws IllegalArgumentException {
        if (!signinId.matches(EMAIL_REGEX))
            throw new IllegalArgumentException("이메일 형식이 아닙니다.");
        return Response.builder(200, memberService.existSigninIdCheck(signinId)).build();
    }

    @GetMapping("/nickNameCheck")
    @Override
    public Response<Boolean> existsByNickName(@RequestParam(value = "nickName") String nickName) throws IllegalArgumentException {
        if (!nickName.matches(NICKNAME_REGEX))
            throw new IllegalArgumentException("4 ~ 20 글자 및 숫자,영어,한글만 가능합니다.");
        return Response.builder(200, memberService.existNicknameCheck(nickName)).build();
    }

    @PostMapping("/signin")
    @Override
    public Response<SignInMemberResponseDTO> memberSignIn(@RequestBody @Valid SignInMemberRequestDTO signInMemberRequestDTO, BindingResult bindingResult) throws BindException{
        if(bindingResult.hasErrors())
            throw new BindException(bindingResult);
        return authService.signinMember(signInMemberRequestDTO);
    }

    @PostMapping(value = "/fileSave",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Override
    public Response<String> fileSave(@RequestPart("multipartFile") MultipartFile multipartFile){
        return Response.builder(200, "string").build();
    }
}
