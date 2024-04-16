package ToyProject.NewDy.REST_API.point.service.decorator;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PointDecorator implements PointDecoratorService {

    private final PointDecoratorService pointDecoratorService;

    @Override
    public int addPoint(int point) {
        return pointDecoratorService.addPoint(point);
    }
}
