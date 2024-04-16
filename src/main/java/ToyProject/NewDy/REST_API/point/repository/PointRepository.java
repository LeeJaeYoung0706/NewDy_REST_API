package ToyProject.NewDy.REST_API.point.repository;

import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.point.domain.Point;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, String> {

//    @Query("select p.pointValue from Point p where p.member.id = :memberId")
//    List<Integer> findPointByMemberId(@Param(value = "memberId") String memberId);
//
//    List<Point> findByMember(Member member);



}
