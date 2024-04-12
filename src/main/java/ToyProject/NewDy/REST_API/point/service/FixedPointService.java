package ToyProject.NewDy.REST_API.point.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value = "fixedPointService")
public class FixedPointService implements PointService {
}
