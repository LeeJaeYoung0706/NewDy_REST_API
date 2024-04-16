package ToyProject.NewDy.REST_API.point.service;

import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.member.enums.MemberGrade;
import ToyProject.NewDy.REST_API.member.enums.PointKind;
import ToyProject.NewDy.REST_API.member.repository.MemberRepository;
import ToyProject.NewDy.REST_API.point.domain.Point;
import ToyProject.NewDy.REST_API.point.domain.QPoint;
import ToyProject.NewDy.REST_API.point.repository.PointRepository;
import ToyProject.NewDy.REST_API.point.service.decorator.DefaultPointDecorator;
import ToyProject.NewDy.REST_API.point.service.decorator.GradePointDecorator;
import ToyProject.NewDy.REST_API.point.service.decorator.PointClient;
import ToyProject.NewDy.REST_API.point.service.decorator.PointDecoratorService;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier(value = "fixedPointService")
@RequiredArgsConstructor
@Slf4j
public class FixedPointService implements PointService {

    private PointDecoratorService pointDecoratorService = new DefaultPointDecorator();
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private boolean GRADE_ADDITION = true;

    @Override
    public void setGRADE_ADDITION(boolean value){
        this.GRADE_ADDITION = value;
    }

    @Override
    @Transactional
    public void addPoint(Member member, int point, PointKind kind) {
        boolean check = false;
        if (GRADE_ADDITION){
            pointDecoratorService = new GradePointDecorator(pointDecoratorService, member.getGarde());
            check = true;
        }

        int savePoint = new PointClient(pointDecoratorService).addPoint(point);
        // 싱글톤 이라서 다시 변경해주어야 합니다.
        if (check) {
            pointDecoratorService = new DefaultPointDecorator();
        }
        Point.createPoint(savePoint, kind, member);
        addSumPointValue(member, savePoint);
    }


    private void addSumPointValue(Member member, int point){
        member.addPoint(point);
    }
}
