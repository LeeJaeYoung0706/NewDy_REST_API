package ToyProject.NewDy.REST_API.point.service;

import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.member.enums.MemberGrade;
import ToyProject.NewDy.REST_API.member.enums.PointKind;
import ToyProject.NewDy.REST_API.member.repository.MemberRepository;
import ToyProject.NewDy.REST_API.point.domain.Point;
import ToyProject.NewDy.REST_API.point.repository.PointRepository;
import ToyProject.NewDy.REST_API.point.service.decorator.DefaultPointDecorator;
import ToyProject.NewDy.REST_API.point.service.decorator.GradePointDecorator;
import ToyProject.NewDy.REST_API.point.service.decorator.PointClient;
import ToyProject.NewDy.REST_API.point.service.decorator.PointDecoratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier(value = "fixedPointService")
@RequiredArgsConstructor
public class FixedPointService implements PointService {

    private PointDecoratorService pointDecoratorService = new DefaultPointDecorator();
    private boolean GRADE_ADDITION = true;
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;

    @Override
    @Transactional
    public void addPoint(Member member, int point, PointKind kind) {

        if (GRADE_ADDITION){
            pointDecoratorService = new GradePointDecorator(pointDecoratorService, member.getGarde());
        }

        int savePoint = new PointClient(pointDecoratorService).addPoint(point);
        Point.createPoint(savePoint, kind, member);
    }
}
