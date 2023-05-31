package dev.meteor.assignmentprm.global.enum_package.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorEnum {

    TEST_ERROR_ENUM(10, "테스트 에러 메시지입니다. ", HttpStatus.INTERNAL_SERVER_ERROR),
    EMPTY_LIST(11, "해당 리스트가 비어있습니다.", HttpStatus.NOT_FOUND),
    EMPTY_REQUEST_DATA(12, "요청값 데이터가 없습니다.", HttpStatus.BAD_REQUEST),
    ALREADY_DATA(13, "중복된 데이터가 존재합니다.", HttpStatus.BAD_REQUEST),
    ALREADY_DELETE_DATA(14, "이미 삭제된 데이터입니다. 다시 요청하여 주십시오.", HttpStatus.BAD_REQUEST),

    NOT_FOUND_DATA(40, "요청한 데이터가 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_CODE_GROUP(41, "요청한 코드 그룹이 존재하지 않습니다.", HttpStatus.NOT_FOUND);

    private final Integer errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorEnum(Integer errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
