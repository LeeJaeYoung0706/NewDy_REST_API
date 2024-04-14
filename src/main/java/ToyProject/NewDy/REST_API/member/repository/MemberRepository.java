package ToyProject.NewDy.REST_API.member.repository;

import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.member.enums.MemberGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Query(name = "Member.findBySigninId")
    Optional<Member> findBySigninId(@Param("signinId") String signinId);

//    @Query(value = "select m.garde from Member m where m.id = :id")
//    Optional<MemberGrade> findGradeById(@Param("id") String id);
}
