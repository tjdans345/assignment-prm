package dev.meteor.assignmentprm.global.enum_package.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorEnum {

    TEST_ERROR_ENUM(10, "테스트 에러 메시지입니다. ", HttpStatus.INTERNAL_SERVER_ERROR);

    private final Integer errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorEnum(Integer errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
