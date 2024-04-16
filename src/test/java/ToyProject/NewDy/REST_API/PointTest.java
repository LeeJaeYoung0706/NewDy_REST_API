package ToyProject.NewDy.REST_API;

import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.auth.service.AuthService;
import ToyProject.NewDy.REST_API.common.lib.response.Response;
import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.member.enums.PointKind;
import ToyProject.NewDy.REST_API.member.service.MemberService;
import ToyProject.NewDy.REST_API.point.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Commit
@Slf4j
public class PointTest {

    @Autowired
    @Qualifier(value = "fixedPointService")
    PointService pointService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    @Qualifier(value = "defaultMemberService")
    MemberService memberService;

    @Test
    @DisplayName("데코레이터 테스트")
    @Transactional
    public void pointDecoratorTest () throws Exception {
        // given
        pointService.setGRADE_ADDITION(true);
        SignUpMemberDTO signUpMemberDTO = new SignUpMemberDTO("testId@naver.com" ,
                bCryptPasswordEncoder.encode("test##22234") ,
                null , "안녕 시티" ,
                " 안녕 스트릿" ,
                " 안녕 집코드" );
        Member member = memberService.memberSave(signUpMemberDTO);
        // when
        pointService.addPoint(member, 100, PointKind.SIGN_IN);
        pointService.setGRADE_ADDITION(false);
        pointService.addPoint(member, 100, PointKind.SIGN_IN);

        Assertions.assertThat(member.getPoint()).isEqualTo(730);
//        int pointByMemberId = pointService.getPointByMember(member.getId());
//        log.info("point by id = {}" , pointByMemberId);

        // then
    }
}
