package toy_project.newdy.rest_api.point.repository;

import toy_project.newdy.rest_api.point.domain.Point;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, String> {

//    @Query("select p.pointValue from Point p where p.member.id = :memberId")
//    List<Integer> findPointByMemberId(@Param(value = "memberId") String memberId);
//
//    List<Point> findByMember(Member member);



}
