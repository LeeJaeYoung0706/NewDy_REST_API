package ToyProject.NewDy.REST_API.member;


import ToyProject.NewDy.REST_API.common.domain.Address;
import ToyProject.NewDy.REST_API.common.repository.AddressRepository;
import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.member.domain.Point;
import ToyProject.NewDy.REST_API.member.enums.PointKind;
import ToyProject.NewDy.REST_API.member.repository.MemberRepository;
import ToyProject.NewDy.REST_API.member.repository.PointRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
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

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("Member Save Test")
    @Transactional
    public void memberSaveTest() {



        Member 멤버 = Member.createMember("테스트 이메일" , Date.valueOf("2023-03-04"), "test@naver.com");
        Point point = Point.createPoint(300L, PointKind.SIGN_IN , 멤버);
        Address 주소 = Address.createAddress("테스트 시티", " 테스트 스트릿 ", " 테스트 집코드 " , 멤버);

        Member 저장멤버 = memberRepository.save(멤버);
        Point save = pointRepository.save(point);
        Address 저장주소 = addressRepository.save(주소);

//        저장멤버.getPointList().remove(0);
//        save.setMember(null);


//        memberRepository.delete(저장멤버);
//        System.out.println("test  == " + save);
//        저장멤버.getAddress().remove(저장주소);

        //객체 맞는지 검증
//        assertThat(저장주소).isEqualTo(저장멤버.getAddress().get(0));
//        assertThat(저장주소.getMember()).isEqualTo(멤버);
//        assertThat(저장주소.getMember()).isEqualTo(저장멤버);
    }
}
