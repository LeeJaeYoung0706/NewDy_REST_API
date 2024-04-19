package toy_project.newdy.rest_api.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import toy_project.newdy.rest_api.auth.dto.SignInMemberRequestDTO;
import toy_project.newdy.rest_api.auth.dto.SignInMemberResponseDTO;
import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.auth.lib.SecurityMemberDetails;
import toy_project.newdy.rest_api.common.lib.response.Response;

public interface AuthService extends UserDetailsService {
    Response<String> memberSave(SignUpMemberRequestDTO signUpMemberDTO);
    Response<SignInMemberResponseDTO> signinMember(SignInMemberRequestDTO signInMemberRequestDTO) throws UsernameNotFoundException;
    SecurityMemberDetails findSecurityMemberDetailsBySigninId(String signinId) throws UsernameNotFoundException;
}
