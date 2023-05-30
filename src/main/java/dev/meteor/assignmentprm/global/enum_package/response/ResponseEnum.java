package dev.meteor.assignmentprm.global.enum_package.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ResponseEnum {

    TEST_CODE(20, "테스트 이넘입니다.");

    private final Integer code;

    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
