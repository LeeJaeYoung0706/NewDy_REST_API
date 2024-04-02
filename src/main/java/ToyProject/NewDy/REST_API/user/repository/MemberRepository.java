package ToyProject.NewDy.REST_API.user.repository;

import ToyProject.NewDy.REST_API.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {


}
