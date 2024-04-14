package ToyProject.NewDy.REST_API.point.service.decorator;

import org.springframework.stereotype.Component;


@Component
public class DefaultPointDecorator implements PointDecoratorService{

    @Override
    public int addPoint(int point) {
        return point;
    }
}
