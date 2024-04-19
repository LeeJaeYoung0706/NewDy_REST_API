package toy_project.newdy.rest_api.auth.dto;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class SignInMemberResponseDTO {

    private String signinId;
    private String token;
    private String nickName;
    private List<String> menuGrantList;
    private String role;

    private SignInMemberResponseDTO(String signinId, String token, String nickName, List<String> menuGrantList, String role) {
        this.signinId = signinId;
        this.token = token;
        this.nickName = nickName;
        this.menuGrantList = menuGrantList;
        this.role = role;
    }

    public static SignInMemberResponseDTO createSignInMemberResponseDTO(String signinId, String token, String nickName, List<String> stringGrantList, String role){
        return new SignInMemberResponseDTO(signinId, token, nickName, stringGrantList, role);
    }
}
