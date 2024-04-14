package ToyProject.NewDy.REST_API.member;


import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.common.domain.Address;
import ToyProject.NewDy.REST_API.common.repository.AddressRepository;
import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.member.enums.MemberGrade;
import ToyProject.NewDy.REST_API.member.service.MemberService;
import ToyProject.NewDy.REST_API.point.domain.Point;
import ToyProject.NewDy.REST_API.member.enums.PointKind;
import ToyProject.NewDy.REST_API.member.repository.MemberRepository;
import ToyProject.NewDy.REST_API.point.repository.PointRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Commit
public class MemberTest {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    @Qualifier(value = "defaultMemberService")
    MemberService memberService;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("Member Save Test")
    @Transactional
    public void memberSaveTest() {
        SignUpMemberDTO signUpMemberDTO = new SignUpMemberDTO("testId@naver.com" , bCryptPasswordEncoder.encode("test##22234") , null , "안녕 시티" , " 안녕 스트릿" , " 안녕 집코드" );
        Member 저장멤버 = memberService.memberSave(signUpMemberDTO);

        Optional<Member> bySigninId = memberRepository.findBySigninId(저장멤버.getSigninId());
        Optional<Member> byId = memberRepository.findById(저장멤버.getId());

        assertThat(저장멤버.getSigninId()).isEqualTo(signUpMemberDTO.getSigninId());
        assertThat(bySigninId.isPresent()).isTrue();
        assertThat(byId.isPresent()).isTrue();

        if (byId.isPresent()) {
            assertThat(bySigninId.get()).isEqualTo(저장멤버);
            assertThat(저장멤버.getAddressList().get(0)).isEqualTo(byId.get().getAddressList().get(0));
        }
    }

    @Test
    @DisplayName("Member get Grade Test")
    @Transactional
    public void memberGetGradeTest() {
        SignUpMemberDTO signUpMemberDTO = new SignUpMemberDTO("testId@naver.com" , bCryptPasswordEncoder.encode("test##22234") , null , "안녕 시티" , " 안녕 스트릿" , " 안녕 집코드" );
        Member 저장멤버 = memberService.memberSave(signUpMemberDTO);

        //Optional<Member> grade = memberRepository.findById(저장멤버.getId());

//        if (grade.isPresent()) {
//            assertThat(grade.get().getGarde()).isEqualTo(MemberGrade.BRONZE);
//        }
    }
}
