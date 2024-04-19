package toy_project.newdy.rest_api.member.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import toy_project.newdy.rest_api.common.domain.Address;
import toy_project.newdy.rest_api.common.domain.DateBaseEntity;
import toy_project.newdy.rest_api.common.enums.YesOrNo;
import toy_project.newdy.rest_api.common.sequences.CustomSequenceGenerator;
import toy_project.newdy.rest_api.member.enums.MemberGrade;
import toy_project.newdy.rest_api.point.domain.Point;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
@Table(name = "member"
        //, uniqueConstraints = {@UniqueConstraint( columnNames = {"signinId"})}
)
@Slf4j
public class Member extends DateBaseEntity implements Serializable {

    private static final long serialVersionUID = 362498820753181245L;

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
//                            value = "member"
//                    )
            },
            type = CustomSequenceGenerator.class)
    @Column(name = "member_id")
    private UUID id;

    /**
     * 기존 OneToMany 이지만 다대일로 변경해서 사용합니다. 성능이슈 및 유지보수를 위해서
     */
    @OneToMany(mappedBy = "member" , cascade = CascadeType.PERSIST , orphanRemoval = true )
    private List<Address> addressList = new ArrayList<>();

    @Column(length = 255, name = "signin_id" , unique = true, nullable = false)
    @Comment("로그인 아이디, 이메일 형식")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{6,18}$")
    private String signinId;

    @Column(length = 40, name = "nick_name" , unique = true, nullable = false)
    @Comment("닉네임")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{4,20}$" , message = "닉네임은 영어,숫자,한글만 가능합니다.")
    private String nickName;

    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    @Comment("생일")
    private Date birth;

    // CascadeType.ALL 사용시 select 2번 나감 ( 트랜잭션 처리 안했을 경우 1번 select 하기 때문에 )
    @OneToMany(mappedBy = "member" ,
            cascade = CascadeType.PERSIST ,
            orphanRemoval = true)
    private List<Point> pointList = new ArrayList<>();

    //탈퇴 여부
    @Column(length = 32,
            name = "secessionYN" ,
            columnDefinition = "varchar(32) default 'N'")
    @Enumerated(EnumType.STRING)
    @Comment("탈퇴 여부 ( 얕은 삭제 )")
    private YesOrNo secessionYesOrNo = YesOrNo.N;


    @Column(length = 32 ,
            name = "grade" ,
            columnDefinition = "varchar(32) default 'BRONZE'")
    @Enumerated(EnumType.STRING)
    @Comment("member 등급 혜텍 전용")
    private MemberGrade garde = MemberGrade.BRONZE;

    @Column(name = "point" ,
            columnDefinition = "bigint default 0")
    @Comment("포인트")
    private long point;


    // 생성자 birth는 null로 포함될 수 있습니다. ( 선택 사항 )
    private Member(Date birth, String signinId, String nickName) {
        this.birth = birth;
        this.signinId = signinId;
        this.nickName = nickName;
    }

    // 비즈니스 로직
    public void deleteMember() {
        this.secessionYesOrNo = YesOrNo.Y;
    }

    public void addPoint(long point) {
        this.point += point;
    }

    public void removePoint(long point) {
        this.point = Math.max(0, this.point - point);
    }

    public static Member createMember(String signinId, Date birth, String nickName) {
        return new Member(birth, signinId, nickName);
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(addressList, member.addressList) && Objects.equals(signinId, member.signinId) && Objects.equals(birth, member.birth) && Objects.equals(pointList, member.pointList) && secessionYesOrNo == member.secessionYesOrNo && garde == member.garde;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressList, signinId, birth, pointList, secessionYesOrNo, garde);
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
