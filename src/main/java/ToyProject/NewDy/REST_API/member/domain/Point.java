package ToyProject.NewDy.REST_API.member.domain;

import ToyProject.NewDy.REST_API.common.sequences.CustomSequenceGenerator;
import ToyProject.NewDy.REST_API.member.enums.PointKind;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point  {

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
                            value = "point"
                    )
            },
            type = CustomSequenceGenerator.class)
    @Column(name = "point_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "accumulation_date" , updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime accumulationDate;

    @Column(name = "point_value")
    @Comment("포인트 값")
    private Long point;

    @Column(name = "kind")
    @Enumerated(EnumType.STRING)
    private PointKind kind;

    // 일대 다 업데이트 처리 테스트
    @Builder
    private Point(Long point, PointKind kind) {
        this.point = point;
        this.kind = kind;
    }

    private void setsMember(Member member) {
        this.member = member;
        member.getPointList().add(this);
    }

    public static Point createPoint(Long point, PointKind kind, Member member) {
        Point result = Point.builder().kind(kind).point(point).build();
        result.setsMember(member);
        return result;
    }

//    /**
//     * 셀렉트 쿼리 방지
//       implements Persistable<String>
//     * @return
//     */
//    @Override
//    public boolean isNew() {
//        return accumulationDate == null;
//    }
}
