package toy_project.newdy.rest_api.common.service;

import toy_project.newdy.rest_api.auth.dto.SignUpMemberRequestDTO;
import toy_project.newdy.rest_api.common.domain.Address;
import toy_project.newdy.rest_api.member.domain.Member;

public interface AddressService {

    Address addressSave(SignUpMemberRequestDTO signUpMemberDTO , Member member);
    Address addressSave(SignUpMemberRequestDTO signUpMemberDTO , String memberId);
}
