package toy_project.newdy.rest_api.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import toy_project.newdy.rest_api.auth.domain.AuthMember;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthMemberRepository extends JpaRepository<AuthMember , UUID> {

    @Query(name = "AuthMember.findBySigninId")
    Optional<AuthMember> findBySigninId(@Param("signinId") String signinId);
}
