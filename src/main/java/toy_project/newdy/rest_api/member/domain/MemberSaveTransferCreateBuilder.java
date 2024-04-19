package toy_project.newdy.rest_api.member.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Optional;

@Getter
public class MemberSaveTransferCreateBuilder {

    private Date birth = null;
    private String signinId;
    private String nickName;

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

    public MemberSaveTransferCreateBuilder nickName(String nickName){
        this.nickName = nickName;
        return this;
    }

    public static MemberSaveTransferCreateBuilder builder(){
        return new MemberSaveTransferCreateBuilder();
    }

    public Member build() {
        if (nickName != null && signinId != null)
            return Member.createMember(signinId, birth, nickName);
        else
            throw new IllegalArgumentException("닉네임과 로그인 아이디가 불명확합니다.");
    }
}
