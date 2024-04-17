package toy_project.newdy.rest_api.point.service;

import toy_project.newdy.rest_api.member.domain.Member;
import toy_project.newdy.rest_api.member.enums.PointKind;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value = "percentPointService")
public class PercentPointService implements PointService{

    @Override
    public void addPoint(Member member, int point, PointKind kind) {

    }

    @Override
    public void setGRADE_ADDITION(boolean value) {

    }
}
