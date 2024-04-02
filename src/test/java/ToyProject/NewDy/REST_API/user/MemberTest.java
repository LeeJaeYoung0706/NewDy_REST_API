package ToyProject.NewDy.REST_API.user;


import ToyProject.NewDy.REST_API.common.domain.Address;
import ToyProject.NewDy.REST_API.common.repository.AddressRepository;
import ToyProject.NewDy.REST_API.user.domain.Member;
import ToyProject.NewDy.REST_API.user.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MemberTest {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Member Save Test")
    public void memberSaveTest() {
        Address address = new Address();
        address.setCity("test");
        address.setZipCode("test");
        address.setStreet("test");
        addressRepository.save(address);

        Member member = new Member();
        member.setAddress(List.of(address));

        memberRepository.save(member);
        Optional<Address> byId = addressRepository.findById(member.getAddress().get(0).getId());

        log.info(" test = {} " , byId.get());


    }
}
