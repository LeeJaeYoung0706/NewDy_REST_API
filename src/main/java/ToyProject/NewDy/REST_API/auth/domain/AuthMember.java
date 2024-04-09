//package ToyProject.NewDy.REST_API.auth.domain;
//
//import ToyProject.NewDy.REST_API.auth.enums.AuthEnum;
//import ToyProject.NewDy.REST_API.common.sequences.CustomSequenceGenerator;
//import ToyProject.NewDy.REST_API.member.domain.Member;
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class AuthMember {
//
//    @Id
//    @GeneratedValue(generator = "custom_generator")
//    @GenericGenerator(name = "custom_generator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(
//                            name = "initial_value",
//                            value = "1"
//                    ), // 시작점
//                    @org.hibernate.annotations.Parameter(
//                            name = "increment_size",
//                            value = "50"
//                    ), // 캐싱 사이즈
//                    @org.hibernate.annotations.Parameter(
//                            name = "prefix",
//                            value = "auth"
//                    )
//            },
//            type = CustomSequenceGenerator.class)
//    @Column(name = "auth_member_id")
//    private String id;
//    @Column(name = "signin_id" , unique = true)
//    private String signinId;
//    @Column(name = "password")
//    private String password;
////    @OneToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "member_id")
////    private Member member;
//    @Column(name = "auth_role")
//    @Enumerated(EnumType.STRING)
//    private AuthEnum authRole;
//    @Column(name = "enable")
//    private boolean enable;
//
//    @Builder
//    private AuthMember(String signinId, String password, Member member, AuthEnum authRole, boolean enable) {
//        this.signinId = signinId;
//        this.password = password;
//        this.member = member;
//        this.authRole = authRole;
//        this.enable = enable;
//    }
//
//    public AuthMember of(String signinId, String password, Member member, AuthEnum authRole){
//        return new AuthMember(signinId , password, member, authRole, true);
//    }
//}
