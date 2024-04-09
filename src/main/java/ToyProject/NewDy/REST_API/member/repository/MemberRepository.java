package ToyProject.NewDy.REST_API.member.repository;

import ToyProject.NewDy.REST_API.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {


}
