package ToyProject.NewDy.REST_API.member.domain;

import ToyProject.NewDy.REST_API.common.domain.Address;
import ToyProject.NewDy.REST_API.common.domain.DateBaseEntity;
import ToyProject.NewDy.REST_API.common.enums.YesOrNo;
import ToyProject.NewDy.REST_API.common.sequences.CustomSequenceGenerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class Member extends DateBaseEntity {

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
    @Column(name = "member_id")
    private String id;

    /**
     * 기존 OneToMany 이지만 다대일로 변경해서 사용합니다. 성능이슈 및 유지보수를 위해서
     */
    @OneToMany(mappedBy = "member" , cascade = CascadeType.PERSIST , orphanRemoval = true )
    private List<Address> addressList = new ArrayList<>();

    @Column(unique = true, name = "email", nullable = false, columnDefinition = "varchar(512)")
    private String email;

    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;
    // CascadeType.ALL 사용시 select 2번 나감 ( 트랜잭션 처리 안했을 경우 1번 select 하기 때문에 )
    @OneToMany(mappedBy = "member" , cascade = CascadeType.PERSIST , orphanRemoval = true)
    private List<Point> pointList = new ArrayList<>();

    //탈퇴 여부
    @Column(name = "secessionYN" , columnDefinition = "varchar(32) default 'N'")
    @Enumerated(EnumType.STRING)
    private YesOrNo secessionYesOrNo = YesOrNo.N;

    @Column(name = "signin_id" , unique = true, nullable = false)
    private String signinId;

    @Builder
    private Member(String email, Date birth, String signinId) {
        this.email = email;
        this.birth = birth;
        this.signinId = signinId;
    }

    public static Member createMember(String email, Date birth, String signinId){
        return Member.builder()
                .email(email)
                .birth(birth)
                .signinId(signinId)
                .build();
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



//

//

//
//    @Column(name = "grade")
//    @Enumerated(EnumType.STRING)
//    private MemberGarde garde;
//
//    @OneToMany(mappedBy = "member")
//    private List<Point> point;

//    @OneToOne(mappedBy = "member")
//    private AuthMember authMember;
}
