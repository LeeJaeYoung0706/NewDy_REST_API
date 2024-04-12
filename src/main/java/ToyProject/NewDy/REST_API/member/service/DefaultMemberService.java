package ToyProject.NewDy.REST_API.member.service;

import ToyProject.NewDy.REST_API.common.repository.AddressRepository;
import ToyProject.NewDy.REST_API.member.repository.MemberRepository;
import ToyProject.NewDy.REST_API.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value = "defaultMemberService")

public class DefaultMemberService implements MemberService {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final PointService pointService;

    public DefaultMemberService(MemberRepository memberRepository, AddressRepository addressRepository, @Qualifier("fixedPointService") PointService pointService) {
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
        this.pointService = pointService;
    }
}
