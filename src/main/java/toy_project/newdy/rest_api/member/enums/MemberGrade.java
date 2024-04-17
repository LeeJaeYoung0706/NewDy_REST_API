package toy_project.newdy.rest_api.member.enums;

import lombok.Getter;

@Getter
public enum MemberGrade {
    BRONZE("BRONZE" , 1.05f),
    SILVER("SILVER" , 1.05f);

    private final String GRADE;
    private final float PointPlus;

    MemberGrade(String GRADE, float pointPlus) {
        this.GRADE = GRADE;
        PointPlus = pointPlus;
    }
}