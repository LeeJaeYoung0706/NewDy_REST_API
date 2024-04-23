package toy_project.newdy.rest_api.auth.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

import static toy_project.newdy.rest_api.common.lib.RegexList.EMAIL_REGEX;
import static toy_project.newdy.rest_api.common.lib.RegexList.NICKNAME_REGEX;
import static toy_project.newdy.rest_api.common.lib.RegexList.PASSWORD_REGEX;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "회원 저장 객체")
public class SignUpMemberRequestDTO {

    @Pattern(regexp = EMAIL_REGEX , message = "이메일 아이디를 입력해주세요.")
    @Schema(description = "이메일 형식 로그인 아이디")
    private String signinId;

    @Pattern(regexp = PASSWORD_REGEX , message = "비밀번호 대소문자, 특수문자, 숫자 중 2개이상 포함 8~20글자로 입력해주세요.")
    @Schema(description = "비밀번호 대소문자, 특수문자, 숫자 중 2개이상 포함 8~20글자")
    private String password;

    @Schema(description = "생일")
    private Date birth;

    @NotBlank
    @Schema(description = "상위 주소")
    private String city;

    @NotBlank
    @Schema(description = "하위 주소")
    private String street;

    @NotBlank
    @Schema(description = "우편 번호")
    private String zipCode;

    @NotBlank
    @Schema(description = "닉네임")
    @Pattern(regexp = NICKNAME_REGEX , message = "닉네임은 영어,숫자,한글만 가능합니다.")
    private String nickName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpMemberRequestDTO that = (SignUpMemberRequestDTO) o;
        return Objects.equals(signinId, that.signinId) && Objects.equals(password, that.password) && Objects.equals(birth, that.birth) && Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(zipCode, that.zipCode) && Objects.equals(nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(signinId, password, birth, city, street, zipCode, nickName);
    }
}
