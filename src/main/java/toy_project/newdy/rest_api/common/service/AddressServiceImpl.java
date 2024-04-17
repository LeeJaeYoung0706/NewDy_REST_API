package toy_project.newdy.rest_api.common.service;

import toy_project.newdy.rest_api.auth.dto.SignUpMemberDTO;
import toy_project.newdy.rest_api.common.domain.Address;
import toy_project.newdy.rest_api.common.repository.AddressRepository;
import toy_project.newdy.rest_api.member.domain.Member;
import toy_project.newdy.rest_api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final MemberService memberService;

    // 오버로딩 member 객체 용
    @Override
    public Address addressSave(SignUpMemberDTO signUpMemberDTO , Member member){
        Address address = Address.createAddress(signUpMemberDTO.getCity(), signUpMemberDTO.getStreet(), signUpMemberDTO.getZipCode(), member);
        return addressRepository.save(address);
    }

    @Override
    public Address addressSave(SignUpMemberDTO signUpMemberDTO , String memberId){
        // 멤버 아이디 찾아서 Member 객체 만드는 로직 추가

        Address address = Address.createAddress(signUpMemberDTO.getCity(), signUpMemberDTO.getStreet(), signUpMemberDTO.getZipCode(), null);
        return addressRepository.save(address);
    }
}
