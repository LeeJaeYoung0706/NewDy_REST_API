package ToyProject.NewDy.REST_API.auth.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AuthEnum {
    ROLE_USER("USER"),
    ROLE_MANAGER("MANAGER"),
    ROLE_ADMIN("ADMIN");

    private final String role;

    AuthEnum(String role) {
        this.role = role;
    }
}
