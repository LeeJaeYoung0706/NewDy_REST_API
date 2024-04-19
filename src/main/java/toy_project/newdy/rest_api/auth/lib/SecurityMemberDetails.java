package toy_project.newdy.rest_api.auth.lib;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import toy_project.newdy.rest_api.auth.domain.AuthMember;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
public class SecurityMemberDetails implements UserDetails {

    private UUID id;
    private String signinId;
    private List<GrantedAuthority> authorityList;
    private String password;
    private boolean enabled;

    private SecurityMemberDetails(UUID id, String signinId, List<GrantedAuthority> authorityList, String password, boolean enabled) {
        this.id = id;
        this.signinId = signinId;
        this.authorityList = authorityList;
        this.password = password;
        this.enabled = enabled;
    }

    // detail 생성 메소드
    public static SecurityMemberDetails createDetails(AuthMember authMember) {
        return new SecurityMemberDetails(authMember.getId(),
                authMember.getSigninId(),
                createGrantedAuthorityList(authMember),
                authMember.getPassword(),
                authMember.isEnable()
        );
    }

    // 권한 리스트 생성 메소드
    private static List<GrantedAuthority> createGrantedAuthorityList(AuthMember authMember){
        // Menu 권한 추가
        List<GrantedAuthority> grantList = Arrays.stream(authMember.getAuthRole().getGrantOptionList())
                .map(authMemberOption -> new SimpleGrantedAuthority(authMemberOption.name())).collect(Collectors.toList());
        // 기본 Role 권한 추가
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authMember.getAuthRole().getRole());
        grantList.add(simpleGrantedAuthority);
        return grantList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.signinId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
