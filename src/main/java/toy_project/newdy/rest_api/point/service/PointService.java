package toy_project.newdy.rest_api.point.service;

import toy_project.newdy.rest_api.member.domain.Member;
import toy_project.newdy.rest_api.member.enums.PointKind;

public interface PointService {

    void addPoint(Member member, int point, PointKind kind);
    void setGRADE_ADDITION(boolean value);
}
