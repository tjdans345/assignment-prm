package dev.meteor.assignmentprm.global.enum_package.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ResponseEnum {

    TEST_CODE(20, "테스트 이넘입니다."),
    CREATE_SUCCESS(21, "데이터 등록에 성공하였습니다."),
    CHECK_SUCCESS(22, "데이터 체크에 성공하였습니다.");

    private final Integer code;

    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
