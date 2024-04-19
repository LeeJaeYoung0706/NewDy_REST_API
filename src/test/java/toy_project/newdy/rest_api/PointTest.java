package toy_project.newdy.rest_api;

import org.springframework.test.context.ActiveProfiles;
import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.member.domain.Member;
import toy_project.newdy.rest_api.member.enums.PointKind;
import toy_project.newdy.rest_api.member.service.MemberService;
import toy_project.newdy.rest_api.point.service.PointService;
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
@ActiveProfiles("test")
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
        SignUpMemberRequestDTO signUpMemberDTO = new SignUpMemberRequestDTO("testId@naver.com" ,
                bCryptPasswordEncoder.encode("test##22234") ,
                null , "안녕 시티" ,
                " 안녕 스트릿" ,
                " 안녕 집코드", " 테스트 닉네임" );
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
