package toy_project.newdy.rest_api.common.lib;

public class RegexList {

    public static final String EMAIL_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
    public static final String PASSWORD_REGEX = "^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?:[0-9]+))$)[A-Za-z\\d~!@#$%^&*()_+=]{8,20}$";
    public static final String NICKNAME_REGEX = "^[a-zA-Z0-9가-힣]{4,20}$";
}
