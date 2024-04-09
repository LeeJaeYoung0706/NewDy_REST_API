package ToyProject.NewDy.REST_API.member.domain;


import ToyProject.NewDy.REST_API.common.sequences.CustomSequenceGenerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SecessionMember {

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
                            value = "secession"
                    )
            },
            type = CustomSequenceGenerator.class)
    @Column(name = "secession_id")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "secessoion_date")
    @Comment("탈퇴일")
    @CreatedDate
    private LocalDateTime secessionDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
