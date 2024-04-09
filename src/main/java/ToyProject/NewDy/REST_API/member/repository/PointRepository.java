package ToyProject.NewDy.REST_API.member.repository;

import ToyProject.NewDy.REST_API.member.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, String> {
}
