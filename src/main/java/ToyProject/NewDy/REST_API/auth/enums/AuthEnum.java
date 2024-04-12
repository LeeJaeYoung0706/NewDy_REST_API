package ToyProject.NewDy.REST_API.auth.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import static ToyProject.NewDy.REST_API.auth.enums.MenuAuthEnum.*;

@Getter
@Slf4j
public enum AuthEnum {

    ROLE_USER("ROLE_USER" , new MenuAuthEnum[]{MY_PAGE, USER }),
    ROLE_MANAGER("ROLE_MANAGER", new MenuAuthEnum[]{MY_PAGE, CMS, USER}),
    ROLE_ADMIN("ROLE_ADMIN", new MenuAuthEnum[]{MY_PAGE, CMS, USER});

    private final String role;
    private final MenuAuthEnum[] grantOptionList;

    AuthEnum(String role, MenuAuthEnum[] grantOptionList) {
        this.role = role;
        this.grantOptionList = grantOptionList;
    }

    /**
     * Security 권한에 따른 Menu 권한여부
     * 예 authMember.getAuthEnum()
     * @param authEnum
     * @param menuAuthEnum
     * @return
     */
    public static boolean hasMenuAuth(AuthEnum authEnum, MenuAuthEnum ...menuAuthEnum) {
        boolean result = false;
        int index = 0;
        for (MenuAuthEnum menuEnum : menuAuthEnum) {
            boolean matchResult = Arrays.stream(authEnum.grantOptionList).anyMatch(match -> match == menuEnum);
//            log.info("test = {} index = {}" , matchResult , index);
            if (index == 0) {
                result = matchResult;
            } else {
                result = matchResult && result;
            }
            index++;
        }
        return result;
    }
}
