package ToyProject.NewDy.REST_API.user.domain;

import ToyProject.NewDy.REST_API.common.sequences.CustomSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
public class Point {

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
                            value = "member"
                    )
            },
            type = CustomSequenceGenerator.class)
    @Column(name = "point_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "accumulation_date")
    @CreatedDate
    private LocalDateTime accumulationDate;

    @Column(name = "point_value")
    @Comment("포인트 값")
    private Long point;
}
