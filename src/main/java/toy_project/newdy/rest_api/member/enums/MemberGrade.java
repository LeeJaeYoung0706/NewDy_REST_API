package toy_project.newdy.rest_api.member.enums;

import lombok.Getter;

/**
 * 멤버 등급 ( 권한은 AuthMember )
 */
@Getter
public enum MemberGrade {
    BRONZE("BRONZE" , 1.00f),
    SILVER("SILVER" , 1.05f);

    private final String GRADE;
    private final float PointPlus;

    MemberGrade(String GRADE, float pointPlus) {
        this.GRADE = GRADE;
        PointPlus = pointPlus;
    }
}
