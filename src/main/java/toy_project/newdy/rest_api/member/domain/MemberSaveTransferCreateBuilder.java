package toy_project.newdy.rest_api.member.domain;

import lombok.Getter;

import java.util.Date;
import java.util.Optional;

@Getter
public class MemberSaveTransferCreateBuilder {

    private Date birth = null;
    private String signinId = null;

    private MemberSaveTransferCreateBuilder() {
    }

    public MemberSaveTransferCreateBuilder birth(Optional<Date> birth){
        birth.ifPresent( (birthDate) -> {
            this.birth = birthDate;
        });
        return this;
    }

    public MemberSaveTransferCreateBuilder signinId(String signinId){
        this.signinId = signinId;
        return this;
    }

    public static MemberSaveTransferCreateBuilder builder(){
        return new MemberSaveTransferCreateBuilder();
    }

    public Member build() {
        return Member.createMember(signinId, birth);
    }
}
