package toy_project.newdy.rest_api.common.domain;

import toy_project.newdy.rest_api.common.sequences.CustomSequenceGenerator;
import toy_project.newdy.rest_api.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"member"} , callSuper = true)
public class Address extends DateBaseEntity {

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
                            value = "address"
                    )
            },
            type = CustomSequenceGenerator.class)
    @Column(name = "address_id")
    private String id;

    @Column(name = "city" , columnDefinition = "varchar(255) not null")
    @Comment("도로명 주소")
    private String city;

    @Column(name = "street" , columnDefinition = "varchar(255) not null")
    @Comment("상세 주소")
    private String street;

    @Column(name = "zip_code" , columnDefinition = "varchar(128)" , nullable = false)
    @Comment("우편 번호")
    private String zipCode;

    /**
     * 기존 OneToMany 이지만 다대일로 변경해서 사용합니다. 성능이슈 및 유지보수를 위해서
     *
     * @NotNull 또는 Column 에 직접 주입 가능
     */
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    private void setMember(Member member) {
        this.member = member;
        // 없으면 영속성 컨텍스트의 member에는 address가 존재하지 않게 됩니다.
        member.getAddressList().add(this);
    }

    private Address(String city, String street, String zipCode, Member member) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        setMember(member);
    }

    public static Address createAddress(String city, String street , String zipCode, Member member){
        return new Address(city, street, zipCode, member);
    }

}
