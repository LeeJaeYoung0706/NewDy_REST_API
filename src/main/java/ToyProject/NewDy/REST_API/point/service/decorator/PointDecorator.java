package ToyProject.NewDy.REST_API.point.service.decorator;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PointDecorator implements PointDecoratorService {

    private final PointDecoratorService pointService;

    @Override
    public int addPoint(int point) {
        return pointService.addPoint(point);
    }
}
