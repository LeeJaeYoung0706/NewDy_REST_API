package ToyProject.NewDy.REST_API.auth;

import ToyProject.NewDy.REST_API.auth.domain.AuthMember;
import ToyProject.NewDy.REST_API.auth.repository.AuthMemberRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Commit
public class AuthRepositoryTest {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthMemberRepository authMemberRepository;


    @Test
    @Transactional
    @DisplayName("회원 가입 시 권한 테이블 저장")
    public void saveAuthMember() {
//        String encode = bCryptPasswordEncoder.encode("Wodud@@1121");
//        AuthMember userAuthMember = AuthMember.createUserAuthMember("testId123@naver.com", encode);
//        AuthMember saveUserAuthMember = authMemberRepository.save(userAuthMember);
//        AuthMember adminAuthMember = AuthMember.createStaffAuthMember("testId123staff@naver.com", encode);
//        authMemberRepository.save(adminAuthMember);
//
//        System.out.println(saveUserAuthMember.toString());
//        // 권한 정상 확인
//        assertThat(adminAuthMember.getAuthRole().getRole()).isEqualTo("ROLE_ADMIN");
////        assertThat(userAuthMember.getAuthRole()).isNull();
//        assertThat(saveUserAuthMember.getAuthRole().getRole()).isEqualTo("ROLE_USER");
    }

    @Test
    @Transactional
    @DisplayName("회원 가입시 이메일 정규식 검증 여부")
    public void emailRegTest() {
//        String encode = bCryptPasswordEncoder.encode("Wodud@@1121");
//
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//
//        Set<ConstraintViolation<AuthMember>> validation1 = validator.validate( AuthMember.createUserAuthMember("testId123@naver.4", encode));
//        assertThat(validation1).isNotEmpty();
//
//        Set<ConstraintViolation<AuthMember>> validation2 = validator.validate( AuthMember.createUserAuthMember("testId123@naver.com", encode));
//        assertThat(validation2).isEmpty();
//
//        Set<ConstraintViolation<AuthMember>> validation3 = validator.validate( AuthMember.createUserAuthMember("testId1233naver5", encode));
//        assertThat(validation3).isNotEmpty();
    }
}
