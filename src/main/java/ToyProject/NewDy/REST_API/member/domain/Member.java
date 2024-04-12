package ToyProject.NewDy.REST_API.member.domain;

import ToyProject.NewDy.REST_API.common.domain.Address;
import ToyProject.NewDy.REST_API.common.domain.DateBaseEntity;
import ToyProject.NewDy.REST_API.common.enums.YesOrNo;
import ToyProject.NewDy.REST_API.common.sequences.CustomSequenceGenerator;
import ToyProject.NewDy.REST_API.member.enums.MemberGrade;
import ToyProject.NewDy.REST_API.point.domain.Point;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
// 정적 쿼리 아이디로 검색
@NamedQuery(
        name = "Member.findBySigninId",
        query = "select m from Member m where m.signinId = :signinId"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@ToString(exclude = {"addressList" , "pointList"} , callSuper = true)
@Table(name = "member")
public class Member extends DateBaseEntity {

    @Id
    @GeneratedValue(generator = "custom_generator")
    @GenericGenerator(name = "custom_generator",
            parameters = {
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
    @Column(name = "member_id")
    private String id;

    /**
     * 기존 OneToMany 이지만 다대일로 변경해서 사용합니다. 성능이슈 및 유지보수를 위해서
     */
    @OneToMany(mappedBy = "member" , cascade = CascadeType.PERSIST , orphanRemoval = true )
    private List<Address> addressList = new ArrayList<>();

    @Column(length = 255, name = "signin_id" , unique = true, nullable = false)
    @Comment("로그인 아이디, 이메일 형식")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$")
    private String signinId;

    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    @Comment("생일")
    private Date birth;

    // CascadeType.ALL 사용시 select 2번 나감 ( 트랜잭션 처리 안했을 경우 1번 select 하기 때문에 )
    @OneToMany(mappedBy = "member" , cascade = CascadeType.PERSIST , orphanRemoval = true)
    private List<Point> pointList = new ArrayList<>();

    //탈퇴 여부
    @Column(length = 32, name = "secessionYN" , columnDefinition = "varchar(32) default 'N'")
    @Enumerated(EnumType.STRING)
    @Comment("탈퇴 여부 ( 얕은 삭제 )")
    private YesOrNo secessionYesOrNo = YesOrNo.N;


    @Column(length = 32 , name = "grade" , columnDefinition = "varchar(32) default 'BRONZE'")
    @Enumerated(EnumType.STRING)
    @Comment("member 등급 혜텍 전용")
    private MemberGrade garde = MemberGrade.BRONZE;

    @Builder
    private Member(Date birth, String signinId) {
        this.birth = birth;
        this.signinId = signinId;
    }

    public static Member createMember(Date birth, String signinId){
        return Member.builder()
                .birth(birth)
                .signinId(signinId)
                .build();
    }
}

//Hibernate:
//    update
//        point
//    set
//        member_id=?
//    where
//        point_id=?
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private List<Point> point = new ArrayList<>();

//    @OneToOne(mappedBy = "member")
//    private AuthMember authMember;
