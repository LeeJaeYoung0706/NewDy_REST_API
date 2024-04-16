package ToyProject.NewDy.REST_API.point.service;

import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.member.enums.PointKind;

public interface PointService {

    void addPoint(Member member, int point, PointKind kind);
    void setGRADE_ADDITION(boolean value);
}
