package ToyProject.NewDy.REST_API.member.domain;

import ToyProject.NewDy.REST_API.common.sequences.CustomSequenceGenerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignInLog {

    @Id
    @GeneratedValue(generator = "custom_generator")
    @GenericGenerator(name = "custom_generator",
            parameters = {
//                    @org.hibernate.annotations.Parameter(
//                            name = "initial_value",
//                            value = "1"
//                    ), // 시작점
                    @org.hibernate.annotations.Parameter(
                            name = "increment_size",
                            value = "50"
                    ), // 캐싱 사이즈
                    @org.hibernate.annotations.Parameter(
                            name = "prefix",
                            value = "signinlog"
                    )
            },
            type = CustomSequenceGenerator.class)
    @Column(name = "signinlog_id")
    private String id;

    @Column(name = "signin_time" , nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime signInTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "memeber_id"
    )
    private Member member;
}
