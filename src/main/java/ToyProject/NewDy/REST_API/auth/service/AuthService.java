package ToyProject.NewDy.REST_API.auth.service;

import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.common.lib.response.Response;

public interface AuthService {
    Response<String> memberSave(SignUpMemberDTO signUpMemberDTO);
}
