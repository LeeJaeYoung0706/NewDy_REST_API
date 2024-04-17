package toy_project.newdy.rest_api.point.service.decorator;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PointClient {

    private final PointDecoratorService pointDecoratorService;

    public int addPoint(int point){
        return pointDecoratorService.addPoint(point);
    }
}
