package ToyProject.NewDy.REST_API.user.domain;


import ToyProject.NewDy.REST_API.common.sequences.CustomSequenceGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
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
    private LocalDateTime secessionDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}