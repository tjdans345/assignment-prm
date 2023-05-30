package dev.meteor.assignmentprm.global.common.domain.dto.response;

import dev.meteor.assignmentprm.global.enum_package.error.ErrorEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponseDTO {

    private Integer errorCode;

    private String message;


    @Builder
    public ErrorResponseDTO(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getErrorCode();
        this.message = errorEnum.getMessage();
    }

    public ErrorResponseDTO(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }



}
