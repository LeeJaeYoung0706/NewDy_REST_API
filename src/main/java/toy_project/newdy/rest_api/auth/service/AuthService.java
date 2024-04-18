package toy_project.newdy.rest_api.auth.service;

import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.common.lib.response.Response;

public interface AuthService {
    Response<String> memberSave(SignUpMemberRequestDTO signUpMemberDTO);
}
