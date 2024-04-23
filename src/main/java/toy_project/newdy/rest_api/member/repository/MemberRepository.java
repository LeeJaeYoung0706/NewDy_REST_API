package toy_project.newdy.rest_api.member.repository;



import toy_project.newdy.rest_api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    @Query(name = "Member.findBySigninId")
    Optional<Member> findBySigninId(@Param("signinId") String signinId);

    boolean existsBySigninId(String signinId);
    boolean existsByNickName(String nickName);

    //    @Query(value = "select m.garde from Member m where m.id = :id")
//    Optional<MemberGrade> findGradeById(@Param("id") String id);


}
