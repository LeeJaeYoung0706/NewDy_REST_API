package toy_project.newdy.rest_api.auth.repository;

import toy_project.newdy.rest_api.auth.domain.AuthMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMemberRepository extends JpaRepository<AuthMember , String> {
}
