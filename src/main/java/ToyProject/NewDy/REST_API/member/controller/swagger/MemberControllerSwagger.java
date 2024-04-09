package ToyProject.NewDy.REST_API.member.controller.swagger;


import ToyProject.NewDy.REST_API.common.lib.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "멤버 컨트롤러" , description = "멤버 관련 기능")
public interface MemberControllerSwagger {

    @Operation(
            summary = "멤버 저장",
            description = "멈버 저장"
    )
    public Response<?> memberSave();

}
