package toy_project.newdy.rest_api.auth.controller.swagger;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import toy_project.newdy.rest_api.auth.dto.SignInMemberRequestDTO;
import toy_project.newdy.rest_api.auth.dto.SignInMemberResponseDTO;
import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.common.lib.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@Tag(name = "AuthenticationController", description = "인증 컨트롤러")
public interface AuthenticationControllerSwagger {


    //region 회원 저장 메소드 memberSave
    @Operation(
            summary = "회원 저장",
            description = " 회원 저장 정보 ( 이메일 아이디, 패스워드, 생일, 주소 )"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200" , description = "성공" , content = {
                            @Content(schema = @Schema(implementation = String.class))
                    }),
                    @ApiResponse(responseCode = "405" , description = "알맞은 값을 입력해주세요."),
                    @ApiResponse(responseCode = "406" , description = "알맞은 값을 입력해주세요."),
                    @ApiResponse(responseCode = "408" , description = "해당 아이디는 존재합니다.")
            }
    )
    Response<String> memberSave(
            @Parameter(description = "회원가입 정보", required = true)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "회원 저장 예제", value = """
                    {
                        "signinId" : "test@naver.com",
                        "password" : "wodud$$1121",
                        "birth" : "2023-04-03",
                        "city" : "경기도 안양시 관악대로",
                        "street" : "e편한세상 아파트",
                        "zipCode" : "37293",
                        "nickName" : "test"
                    }
                """)
                    }
                    )) SignUpMemberRequestDTO signUpMemberDTO, BindingResult bindingResult) throws BindException;
    //endregion


    //region 회원 로그인 메소드 memberSignIn()
    @Operation(
        summary = "회원 로그인",
        description = "로그인 ( 이메일 아이디, 패스워드 )"
    )
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200" , description = "성공" , content = {
                @Content(schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "400" , description = "해당하는 유저를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "406" , description = "아이디 또는 비밀번호가 일치하지 않습니다.")
        }
    )
    Response<SignInMemberResponseDTO> memberSignIn(
            @Parameter(description = "로그인", required = true)
        @RequestBody(
            content = @Content(examples = {
                @ExampleObject(name = "로그인 예제", value = """
                    {
                        "signinId" : "test@naver.com",
                        "password" : "wodud$$1121"
                    }
                """)
            }
        )) SignInMemberRequestDTO signInMemberRequestDTO, BindingResult bindingResult) throws BindException;
    //endregion

    @Operation(
        summary = "회원 로그인 아이디 중복 검사",
        description = "회원 로그인 아이디 중복 검사 ( 이메일 )"
    )
    @ApiResponses(
        value = {
                @ApiResponse(responseCode = "200" , description = "성공 // true -> 존재 합니다 , false -> 존재하지 않습니다."),
                @ApiResponse(responseCode = "406" , description = "이메일 형식으로 입력해주세요.")
        }
    )
    Response<Boolean> existsBySigninId(@Parameter(description = "로그인 아이디", required = true, example = "test@naver.com") String signinId) throws IllegalArgumentException;

    @Operation(
            summary = "회원 닉네임 중복 검사",
            description = "회원 닉네임 중복 검사 ( 이메일 )"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200" , description = "성공 // true -> 존재 합니다 , false -> 존재하지 않습니다."),
                    @ApiResponse(responseCode = "406" , description = "4 ~ 20 글자 및 숫자,영어,한글만 가능합니다.")
            }
    )
    Response<Boolean> existsByNickName(@Parameter(description = "닉네임", required = true, example = "exampleNickName") String nickName) throws IllegalArgumentException;


    @Operation(
            summary = "파일 저장",
            description = " 파일 저장 정보 ( 이메일 아이디, 패스워드, 생일, 주소 )"
    )
    Response<String> fileSave(@Parameter(description = "이미지", required = true) MultipartFile multipartFile);
}
