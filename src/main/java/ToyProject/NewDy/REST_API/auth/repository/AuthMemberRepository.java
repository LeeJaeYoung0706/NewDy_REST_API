package ToyProject.NewDy.REST_API.auth.repository;

import ToyProject.NewDy.REST_API.auth.domain.AuthMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMemberRepository extends JpaRepository<AuthMember , String> {
}
