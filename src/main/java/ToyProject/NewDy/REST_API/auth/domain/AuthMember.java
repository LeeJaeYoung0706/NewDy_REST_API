package ToyProject.NewDy.REST_API.auth.domain;

import ToyProject.NewDy.REST_API.auth.enums.AuthEnum;
import ToyProject.NewDy.REST_API.common.sequences.CustomSequenceGenerator;
import ToyProject.NewDy.REST_API.user.domain.Member;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class AuthMember {

    @Id
    @GeneratedValue(generator = "custom_generator")
    @GenericGenerator(name = "custom_generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "initial_value",
                            value = "1"
                    ), // 시작점
                    @org.hibernate.annotations.Parameter(
                            name = "increment_size",
                            value = "50"
                    ), // 캐싱 사이즈
                    @org.hibernate.annotations.Parameter(
                            name = "prefix",
                            value = "auth"
                    )
            },
            type = CustomSequenceGenerator.class)
    @Column(name = "auth_member_id")
    private String id;
    @Column(name = "signin_id" , unique = true)
    private String signinId;
    @Column(name = "password")
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name = "auth_grade")
    @Enumerated(EnumType.STRING)
    private AuthEnum authGrade;
}
