package toy_project.newdy.rest_api.auth.controller.swagger;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
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

    @Operation(
            summary = "파일 저장",
            description = " 파일 저장 정보 ( 이메일 아이디, 패스워드, 생일, 주소 )"
    )
    Response<String> fileSave(@Parameter(description = "이미지", required = true) MultipartFile multipartFile);
}
