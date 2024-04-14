package ToyProject.NewDy.REST_API.common.service;

import ToyProject.NewDy.REST_API.auth.dto.SignUpMemberDTO;
import ToyProject.NewDy.REST_API.common.domain.Address;
import ToyProject.NewDy.REST_API.common.repository.AddressRepository;
import ToyProject.NewDy.REST_API.member.domain.Member;
import ToyProject.NewDy.REST_API.member.service.MemberService;
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
