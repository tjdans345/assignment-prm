package dev.meteor.assignmentprm.global.enum_package.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorEnum {

    TEST_ERROR_ENUM(10, "테스트 에러 메시지입니다. ", HttpStatus.INTERNAL_SERVER_ERROR),
    EMPTY_LIST(11, "데이터가 없습니다.", HttpStatus.NOT_FOUND),
    EMPTY_REQUEST_DATA(12, "요청값 데이터가 없습니다.", HttpStatus.BAD_REQUEST),
    ALREADY_DATA(13, "중복된 데이터가 존재합니다.", HttpStatus.BAD_REQUEST);

    private final Integer errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorEnum(Integer errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
