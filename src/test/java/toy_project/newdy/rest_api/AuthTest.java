package toy_project.newdy.rest_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import toy_project.newdy.rest_api.auth.domain.AuthMember;
import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.auth.enums.AuthEnum;
import toy_project.newdy.rest_api.auth.enums.MenuAuthEnum;
import toy_project.newdy.rest_api.auth.repository.AuthMemberRepository;
import toy_project.newdy.rest_api.auth.service.AuthService;
import toy_project.newdy.rest_api.common.lib.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthMemberRepository authMemberRepository;

    @Autowired
    AuthService authService;

//    @BeforeEach
//    public void init() {
//        excute();
//    }

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

    @Test
    @Transactional
    @DisplayName("회원 가입시 이메일 정규식 검증 여부")
    public void emailRegTest() {
        String encode = bCryptPasswordEncoder.encode("Wodud@@1121");

        SignUpMemberRequestDTO errorSignUpMemberDTO = new SignUpMemberRequestDTO("testnaver.com", "wodud!!11", null, "테스트 시티", "테스트 스트릿", "테스트 집코드", "테스트 닉네임");
        SignUpMemberRequestDTO normalSignUpMemberDTO = new SignUpMemberRequestDTO("test@naver.com", "wodud!!11", null, "테스트 시티", "테스트 스트릿", "테스트 집코드", "테스트 닉네임");
        SignUpMemberRequestDTO error2SignUpMemberDTO = new SignUpMemberRequestDTO("test@navercom", "wodud!!11", null, "테스트 시티", "테스트 스트릿", "테스트 집코드", "테스트 닉네임");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<AuthMember>> validation1 = validator.validate( AuthMember.createUserAuthMember(errorSignUpMemberDTO, encode));
        assertThat(validation1).isNotEmpty();

        Set<ConstraintViolation<AuthMember>> validation2 = validator.validate( AuthMember.createUserAuthMember(normalSignUpMemberDTO, encode));
        assertThat(validation2).isEmpty();

        Set<ConstraintViolation<AuthMember>> validation3 = validator.validate( AuthMember.createUserAuthMember(error2SignUpMemberDTO, encode));
        assertThat(validation3).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("회원 가입 시 권한 테이블 저장")
    public void saveAuthMember() {
        SignUpMemberRequestDTO signUpMemberDTO = new SignUpMemberRequestDTO("testId@naver.com" , bCryptPasswordEncoder.encode("test##22234") , null , "안녕 시티" , " 안녕 스트릿" , " 안녕 집코드" , "테스트 닉네임");
        Response<String> stringResponse = authService.memberSave(signUpMemberDTO);
        Assertions.assertThat(stringResponse.getData()).isEqualTo(signUpMemberDTO.getSigninId());
    }

    @Test
    @DisplayName("비밀번호 바인딩 테스트")
    public void MockMvcAuthSavePwdBindingTest() throws Exception {
        SignUpMemberRequestDTO errorSignUpMemberDTO = new SignUpMemberRequestDTO("test@naver.com", "112233", null, "테스트 시티", "테스트 스트릿", "테스트 집코드", "테스트 닉네임");
        SignUpMemberRequestDTO normalSignUpMemberDTO = new SignUpMemberRequestDTO("test2@naver.com", "wodud!!11", null, "테스트 시티", "테스트 스트릿", "테스트 집코드", "테스트 닉네임");

        // binding Error
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(errorSignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().is(405))
                .andReturn();

        // normal
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(normalSignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("멤버 저장 로직 정상 작동 확인")
    public void MockMvcMemberTransferTest() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SignUpMemberRequestDTO normalSignUpMemberDTO = new SignUpMemberRequestDTO("test2@naver.com", "wodud!!11", simpleDateFormat.parse("2024-04-03") , "테스트 시티", "테스트 스트릿", "테스트 집코드" , "테스트 닉네임");

        // normal
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(normalSignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("닉네임 바인딩 테스트")
    @Transactional
    public void 닉네임정규식테스트() throws Exception {
        //given
        SignUpMemberRequestDTO errorSignUpMemberDTO = new SignUpMemberRequestDTO("testId@naver.com" , "test##22234" , null , "안녕 시티" , " 안녕 스트릿" , " 안녕 집코드", "테스트 닉네임$%#$%");
        SignUpMemberRequestDTO error2SignUpMemberDTO = new SignUpMemberRequestDTO("testId@naver.com" , "test##22234" , null , "안녕 시티" , " 안녕 스트릿" , " 안녕 집코드", "테스트말랑카우아아아아ㅁㅁㅁ");
        SignUpMemberRequestDTO normalSignUpMemberDTO = new SignUpMemberRequestDTO("test3@naver.com", "wodud!!11", null, "테스트 시티", "테스트 스트릿", "테스트 집코드", "테스트닉네임");

        // binding Error
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(errorSignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().is(405))
                .andReturn();

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(error2SignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().is(405))
                .andReturn();

        // normal
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(normalSignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("닉네임 욕 테스트")
    @Transactional
    public void 닉네임욕테스트() throws Exception {
        //given
        SignUpMemberRequestDTO errorSignUpMemberDTO = new SignUpMemberRequestDTO("testId@naver.com" , "test##22234" , null , "안녕 시티" , " 안녕 스트릿" , " 안녕 집코드", "테스트시발");
        SignUpMemberRequestDTO error2SignUpMemberDTO = new SignUpMemberRequestDTO("testId@naver.com" , "test##22234" , null , "안녕 시티" , " 안녕 스트릿" , " 안녕 집코드", "테스트개새끼");
        SignUpMemberRequestDTO normalSignUpMemberDTO = new SignUpMemberRequestDTO("test3@naver.com", "wodud!!11", null, "테스트 시티", "테스트 스트릿", "테스트 집코드", "테스트닉네임");

        // binding Error
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(errorSignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().is(405))
                .andReturn();

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(error2SignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().is(405))
                .andReturn();

        // normal
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(normalSignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("아이디 바인딩 테스트")
    public void MockMvcAuthSaveSigninIdBindingTest () throws Exception {
        SignUpMemberRequestDTO errorSignUpMemberDTO = new SignUpMemberRequestDTO("testnaver.com", "wodud!!11", null, "테스트 시티", "테스트 스트릿", "테스트 집코드", "테스트 닉네임");
        SignUpMemberRequestDTO normalSignUpMemberDTO = new SignUpMemberRequestDTO("test3@naver.com", "wodud!!11", null, "테스트 시티", "테스트 스트릿", "테스트 집코드", "테스트 닉네임");
        SignUpMemberRequestDTO error2SignUpMemberDTO = new SignUpMemberRequestDTO("test4@navercom", "wodud!!11", null, "테스트 시티", "테스트 스트릿", "테스트 집코드", "테스트 닉네임");
        // binding Error
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(errorSignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().is(405))
                .andReturn();
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(error2SignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().is(405))
                .andReturn();

        // normal
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(normalSignUpMemberDTO))
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }




}
