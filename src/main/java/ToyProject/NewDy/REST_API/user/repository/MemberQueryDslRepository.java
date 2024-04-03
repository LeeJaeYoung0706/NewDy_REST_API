package ToyProject.NewDy.REST_API.user.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberQueryDslRepository {
    // 트랜잭션 단위로 프록시 entityManager를 지원하기 때문에 동시성 문제를 걱정하지 않아도 됩니다.
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

}
