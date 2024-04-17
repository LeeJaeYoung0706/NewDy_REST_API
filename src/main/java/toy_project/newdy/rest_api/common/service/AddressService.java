package toy_project.newdy.rest_api.common.service;

import toy_project.newdy.rest_api.auth.dto.SignUpMemberDTO;
import toy_project.newdy.rest_api.common.domain.Address;
import toy_project.newdy.rest_api.member.domain.Member;

public interface AddressService {

    Address addressSave(SignUpMemberDTO signUpMemberDTO , Member member);
    Address addressSave(SignUpMemberDTO signUpMemberDTO , String memberId);
}
