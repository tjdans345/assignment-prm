package dev.meteor.assignmentprm.global.common.domain.dto.response;

import dev.meteor.assignmentprm.global.enum_package.response.ResponseEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDTO<T> {

    private Integer code;
    private String message;
    private T content;

    @Builder
    public ResponseDTO(ResponseEnum responseEnum, T content) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.content = content;
    }

    public ResponseDTO(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.content = null;
    }


}
