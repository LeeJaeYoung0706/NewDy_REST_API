package toy_project.newdy.rest_api.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "회원 로그인 객체")
public class SignInMemberRequestDTO {

    @Schema(description = "이메일 형식 로그인 아이디")
    private String signinId;

    @Schema(description = "비밀번호 대소문자, 특수문자, 숫자 중 2개이상 포함 8~20글자")
    private String password;
}
