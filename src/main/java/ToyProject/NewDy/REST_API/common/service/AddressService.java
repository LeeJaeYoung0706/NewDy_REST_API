package ToyProject.NewDy.REST_API.common.service;

import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.common.domain.Address;
import ToyProject.NewDy.REST_API.member.domain.Member;

public interface AddressService {

    Address addressSave(SignUpMemberDTO signUpMemberDTO , Member member);
    Address addressSave(SignUpMemberDTO signUpMemberDTO , String memberId);
}
