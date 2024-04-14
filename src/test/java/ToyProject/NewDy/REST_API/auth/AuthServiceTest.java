package ToyProject.NewDy.REST_API.auth;

import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.auth.service.AuthService;
import ToyProject.NewDy.REST_API.common.lib.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Commit
public class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @Transactional
    @DisplayName("회원 가입 시 권한 테이블 저장")
    public void saveAuthMember() {
        SignUpMemberDTO signUpMemberDTO = new SignUpMemberDTO("testId@naver.com" , bCryptPasswordEncoder.encode("test##22234") , null , "안녕 시티" , " 안녕 스트릿" , " 안녕 집코드" );
        Response<String> stringResponse = authService.memberSave(signUpMemberDTO);

        Assertions.assertThat(stringResponse.getData()).isEqualTo(signUpMemberDTO.getSigninId());

    }
}
