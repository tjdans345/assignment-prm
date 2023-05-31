package dev.meteor.assignmentprm.global.enum_package.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ResponseEnum {

    FIND_SUCCESS(10, "요청한 데이터 조회에 성공하였습니다."),
    CREATE_SUCCESS(21, "데이터 등록에 성공하였습니다."),
    UPDATE_SUCCESS(22, "데이터 수정에 성공하였습니다."),
    DELETE_SUCCESS(23, "데이터 삭제에 성공하였습니다."),
    CHECK_SUCCESS(24, "데이터 체크에 성공하였습니다.");

    private final Integer code;

    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
