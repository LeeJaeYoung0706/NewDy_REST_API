package toy_project.newdy.rest_api.auth.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import toy_project.newdy.rest_api.auth.lib.VilificationList;

import java.util.ArrayList;
import java.util.List;

/**
 * 닉네임 욕 필터링 시스템
 */
@Service
@Slf4j
public class NickCheckNameService {

    private static List<String> vilificationList = new ArrayList<>();

    @PostConstruct
    public void setVilificationList() {
        this.vilificationList = VilificationList.getInitVilificationList();
    }

    /**
     * 닉네임 욕 필터링 시스템
     * @param nickName
     * @return
     */
    public boolean checkNickName(String nickName) {
        //Validation @Pattern 처리가 되어있어서 Empty 체크는 하지 않습니다.
        String trimNickName = nickName.trim();
        String excludeNumberNickName = nickNameExcludeNumber(trimNickName);
        boolean firstResult = vilificationExistCheck(excludeNumberNickName);
        log.info(String.valueOf(firstResult));

        return firstResult;
    }

    private String nickNameExcludeNumber(String trimNickName) {
        return trimNickName.replaceAll("[0-9]" , "");
    }

    private boolean vilificationExistCheck(String excludeNumberNickName) {
        boolean contains = vilificationList.contains(excludeNumberNickName);
        boolean matches = excludeNumberNickName.matches(createRegex());
        return contains || matches;
    }

    /**
     * 정규식 완성 메소드
     * @return
     */
    private String createRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".*");
        for (int index = 0; index < vilificationList.size(); index++) {
            if (index == 0)
                stringBuilder.append("[");

            stringBuilder.append(vilificationList.get(index));

            if (index == vilificationList.size() - 1)
                stringBuilder.append("]");
            else
                stringBuilder.append(", ");
        }
        stringBuilder.append(".*");
        return stringBuilder.toString();
    }

    /**
     * 금칙어 추가하기
     * @param vilificationValue
     */
    public void addVilificationList(String vilificationValue) {
        vilificationList.add(vilificationValue);
    }

    /**
     * 금칙어 제외하기
     * @param vilificationValue
     */
    public void removeVilificationList(String vilificationValue) {
        vilificationList.remove(vilificationValue);
    }
}
