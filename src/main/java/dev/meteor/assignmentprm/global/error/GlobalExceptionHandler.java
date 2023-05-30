package dev.meteor.assignmentprm.global.error;


import dev.meteor.assignmentprm.global.common.domain.dto.response.ErrorResponseDTO;
import dev.meteor.assignmentprm.global.error.CommonException.CommonException;
import dev.meteor.assignmentprm.global.error.CommonException.ValidationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 일반적인 개발 Exception 처리 Method
     * @param e CommonException
     * @return ResponseEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(CommonException.class)
    protected ResponseEntity<ErrorResponseDTO> commonExceptionHandler(CommonException e) {
        return ResponseEntity.status(e.getErrorEnum().getHttpStatus())
                .body(ErrorResponseDTO.builder()
                        .errorEnum(e.getErrorEnum())
                        .build());
    }

    /**
     * Request Data 벨리데이션 체크 익셉션 처리 Method
     * @param e ValidationException Message
     * @return HttpEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(ValidationException.class)
    protected HttpEntity<ErrorResponseDTO> validationException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(400, e.getMessage()));
    }

    /**
     * 잘못된 데이터 형식으로 request 를 요청 시 발생하는 임의적인 에러를 핸들링 해주기 위한 Method
     * @param e HttpMessageNotReadableException
     * @return HttpEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected HttpEntity<ErrorResponseDTO> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(
                                400,
                                "요청 데이터 타입이 올바르지 않습니다. 데이터 타입을 확인하시고 다시 시도하여 주십시오. [DATA_EMPTY]"
                        )
                );
    }

    /**
     * 무결성 제약조건에 위배되는 예외 처리 Method
     * @param e SQLIntegrityConstraintViolationException
     * @return HttpEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected HttpEntity<ErrorResponseDTO> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(
                        400,
                        "중복되는 값을 요청했습니다. 값을 수정 후 다시 요청하여 주십시오. [" + e.getMessage() +"]"
                ));
    }


}
