package toy_project.newdy.rest_api.auth.enums;

import lombok.Getter;

/**
 * 확장성을 위해서 페이지 접근에 대한 권한 세부처리 또는 기능에 대한 권한 세부처리가 들어갈 가능성을
 * 염두하고 2중 List 처리
 */
@Getter
public enum MenuAuthEnum {

    MY_PAGE, CMS, USER
}
