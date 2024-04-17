package toy_project.newdy.rest_api.point.service;

import toy_project.newdy.rest_api.member.domain.Member;
import toy_project.newdy.rest_api.member.enums.PointKind;
import toy_project.newdy.rest_api.member.repository.MemberRepository;
import toy_project.newdy.rest_api.point.domain.Point;
import toy_project.newdy.rest_api.point.repository.PointRepository;
import toy_project.newdy.rest_api.point.service.decorator.DefaultPointDecorator;
import toy_project.newdy.rest_api.point.service.decorator.GradePointDecorator;
import toy_project.newdy.rest_api.point.service.decorator.PointClient;
import toy_project.newdy.rest_api.point.service.decorator.PointDecoratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
