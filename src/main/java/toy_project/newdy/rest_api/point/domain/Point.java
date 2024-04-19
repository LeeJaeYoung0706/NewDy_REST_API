package toy_project.newdy.rest_api.point.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import toy_project.newdy.rest_api.common.sequences.CustomSequenceGenerator;
import toy_project.newdy.rest_api.member.domain.Member;
import toy_project.newdy.rest_api.member.enums.PointKind;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 포인트 관련 Entity
 * 기능 정리
 * 1. Member가 회원가입 또는 출석체크 등 어떠한 액션을 취할 때 적립
 * 2. 포인트가 1년이 지나면 삭제 되도록 배치 스케줄러 등록
 */
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"member"} , callSuper = true)
@DynamicUpdate
public class Point  {

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
//                            value = "point"
//                    )
            },
            type = CustomSequenceGenerator.class)
    @Column(name = "point_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    //referencedColumnName 를 생략하는 이유는 자동으로 상대 테이블의 PK를 잡아주기 때문입니다. ( 생략 시 )
    private Member member;

    @Column(name = "accumulation_date" , updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("포인트 적립일")
    private LocalDateTime accumulationDate;

    @Column(name = "point_value")
    @Comment("포인트 량")
    private int pointValue;

    @Column(name = "kind")
    @Enumerated(EnumType.STRING)
    private PointKind kind;

    // 일대 다 업데이트 처리 테스트
    @Builder
    private Point(int pointValue, PointKind kind) {
        this.pointValue = pointValue;
        this.kind = kind;
    }

    /**
     * 편의 메소드
     * @param member
     */
    private void setMember(Member member) {
        this.member = member;
        member.getPointList().add(this);
    }

    public static Point createPoint(int pointValue, PointKind kind, Member member) {
        Point result = Point.builder()
                .kind(kind)
                .pointValue(pointValue)
                .build();
        result.setMember(member);
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
