package ToyProject.NewDy.REST_API.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberLoginRequestDTO {

    private String id;
    private String password;

    @QueryProjection
    public MemberLoginRequestDTO(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
