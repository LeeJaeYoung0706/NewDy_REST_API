package toy_project.newdy.rest_api;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import toy_project.newdy.rest_api.auth.service.NickCheckNameService;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class NickNameServiceTest {

    @Autowired
    NickCheckNameService nickCheckNameService;

    @Test
    @DisplayName("nickNameTest")
    public void nickNameTest () throws Exception {

        // given
        String test1NickName = "시1발";
        String test2NickName = "시발";
        String test3NickName = "개새3끼";
        String test4NickName = "시발안녕";
        String normal1 = "안녕";
        String normal2 = "아이";

        // when
        boolean true1 = nickCheckNameService.checkNickName(test1NickName);
        boolean true2 = nickCheckNameService.checkNickName(test2NickName);
        boolean true3 = nickCheckNameService.checkNickName(test3NickName);
        boolean true4 = nickCheckNameService.checkNickName(test4NickName);
        boolean false1 = nickCheckNameService.checkNickName(normal1);
        boolean false2 =  nickCheckNameService.checkNickName(normal2);

        // then
        Assertions.assertThat(true1).isTrue();
        Assertions.assertThat(true2).isTrue();
        Assertions.assertThat(true3).isTrue();
        Assertions.assertThat(true4).isTrue();
        Assertions.assertThat(false1).isFalse();
        Assertions.assertThat(false2).isFalse();
    }
}
