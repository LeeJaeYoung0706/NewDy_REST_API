package toy_project.newdy.rest_api.auth.domain;

import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.auth.enums.AuthEnum;
import toy_project.newdy.rest_api.common.sequences.CustomSequenceGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

/**
 * auth Service가 MSA 형태로 변화한다면 Member Service의 Member Entity와 연관관계를 맺기 어려워 짐으로
 * signInId를 공유하고 이를 통해 페인클라이언트나 웹클라이언트로 접근하는 방식으로 선택하려고합니다.
 *
 *  password 를 Entity안에서 의존성 주입을 받아 암호화하는 것은 좋지 못합니다.
 * 우선 엔티티 클래스는 스프링 컨테이너가 관리하는 빈이 아니기 때문에 기본적인 방법으로는 스프링의 의존성 주입을 받을 수 없습니다. 단 스프링을 사용할 때 엔티티에 의존성 주입을 받게 할 수 있는 방법이 있는데, 스프링 load time weaving 으로 검색해보시면 방법이 있습니다.
 *
 * 그런데 사실 이런 방식을 저도 과거에 시도해본적이 있는데, 결국 이게 유지보수성을 많이 떨어뜨리고, 테스트를 어렵게한 다는 것을 경험으로 알게 되었습니다.
 *
 * 그래서 저는 엔티티가 필드를 통에 외부 의존성을 강하게 가지는 것은 권장하지 않습니다.
 * 김영한님의 말.
 *
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 정적 쿼리 아이디로 검색
@NamedQuery(
        name = "AuthMember.findBySigninId",
        query = "select a from AuthMember a where a.signinId = :signinId"
)
@ToString(exclude = {})
public class AuthMember {

    @Id
    @GeneratedValue(generator = "custom_generator")
    @GenericGenerator(name = "custom_generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "increment_size",
                            value = "50"
                    ), // 캐싱 사이즈
//                    @org.hibernate.annotations.Parameter(
//                            name = "prefix",
//                            value = "auth"
//                    )
            },
            type = CustomSequenceGenerator.class)
    @Column(name = "auth_member_id")
    private UUID id;

    @Column(length = 255, name = "signin_id" , unique = true, nullable = false)
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$")
    @Comment("email 형식의 로그인 아이디")
    private String signinId;

    @Column(name = "password")
    @Comment("회원 비밀번호")
    private String password;
    // 초기화 안할 경우 객체 조회시 null로 리턴함으로 주의
    //, columnDefinition = "varchar(10) default 'ROLE_USER'"
    @Column(length = 10, name = "auth_role")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ROLE_USER'")
    @Comment("security role")
    private AuthEnum authRole = AuthEnum.ROLE_USER;

    @Column(name = "enable")
    @ColumnDefault("true")
    @Comment("회원 활성화 여부")
    private boolean enable = true;

    private AuthMember(String signinId, String password, AuthEnum authRole) {
        this.signinId = signinId;
        this.password = password;
        this.authRole = authRole;
    }

    private AuthMember(String signinId, String password) {
        this.signinId = signinId;
        this.password = password;
    }

    public static AuthMember createUserAuthMember(SignUpMemberRequestDTO signUpMemberDTO, String encodePassword){
        return new AuthMember(signUpMemberDTO.getSigninId(), encodePassword);
    }

    public static AuthMember createAdminAuthMember(SignUpMemberRequestDTO signUpMemberDTO, String encodePassword){
        return new AuthMember(signUpMemberDTO.getSigninId(), encodePassword , AuthEnum.ROLE_ADMIN);
    }

}
