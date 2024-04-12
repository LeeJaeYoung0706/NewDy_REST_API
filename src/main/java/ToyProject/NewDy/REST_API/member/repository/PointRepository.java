package ToyProject.NewDy.REST_API.member.repository;

import ToyProject.NewDy.REST_API.member.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, String> {
}
