package ToyProject.NewDy.REST_API.point.service;

import ToyProject.NewDy.REST_API.member.enums.MemberGrade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value = "percentPointService")
public class PercentPointService implements PointService{
}
