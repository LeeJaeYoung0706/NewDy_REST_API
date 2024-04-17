package toy_project.newdy.rest_api.point.service.decorator;

import org.springframework.stereotype.Component;


public class DefaultPointDecorator implements PointDecoratorService{

    @Override
    public int addPoint(int point) {
        return point;
    }
}
