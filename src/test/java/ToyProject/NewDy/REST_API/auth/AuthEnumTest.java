package ToyProject.NewDy.REST_API.auth;

import ToyProject.NewDy.REST_API.auth.enums.AuthEnum;
import ToyProject.NewDy.REST_API.auth.enums.MenuAuthEnum;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class AuthEnumTest {

    @Test
    @DisplayName("인증 관련 Enum 테스트")
    public void 인증관련이넘메소드테스트(){

        // 값 테스트
        assertThat(AuthEnum.ROLE_ADMIN.getRole()).isEqualTo("ROLE_ADMIN");
        assertThat(AuthEnum.ROLE_MANAGER.getRole()).isEqualTo("ROLE_MANAGER");
        assertThat(AuthEnum.ROLE_USER.getRole()).isEqualTo("ROLE_USER");


        // hasMenuAuth 테스트
        boolean adminAuth = AuthEnum.hasMenuAuth(AuthEnum.ROLE_ADMIN, MenuAuthEnum.CMS, MenuAuthEnum.MY_PAGE);
        assertThat(adminAuth).isTrue();

        boolean userAuth = AuthEnum.hasMenuAuth(AuthEnum.ROLE_USER, MenuAuthEnum.CMS, MenuAuthEnum.MY_PAGE);
        assertThat(userAuth).isFalse();
    }
}
