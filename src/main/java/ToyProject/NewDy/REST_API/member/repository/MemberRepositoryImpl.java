package ToyProject.NewDy.REST_API.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryQueryDsl{

    private final JPAQueryFactory jpaQueryFactory;
}
