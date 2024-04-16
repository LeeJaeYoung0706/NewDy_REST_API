package ToyProject.NewDy.REST_API.point.service.decorator;

import ToyProject.NewDy.REST_API.member.enums.MemberGrade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class GradePointDecorator extends PointDecorator{


    private MemberGrade memberGrade;

    public GradePointDecorator(PointDecoratorService pointService, MemberGrade memberGrade) {
        super(pointService);
        this.memberGrade = memberGrade;
    }

    @Override
    public int addPoint(int point) {
        return super.addPoint(gradeDiscount(point));
    }

    private int gradeDiscount(int point){
        int points = switch (memberGrade){
            case BRONZE -> Math.round(point * MemberGrade.BRONZE.getPointPlus());
            case SILVER -> Math.round(point * MemberGrade.SILVER.getPointPlus());
        };
        log.info("pointDeco = {} " , points);
        return points;
    }
}
