package dev.meteor.assignmentprm.global.aop.annotation;


import dev.meteor.assignmentprm.global.common.domain.dto.response.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

/**
 * 스웨거 공통 응답 Display 를 위한 커스텀 어노테이션
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ApiResponses(
        value = {
                @ApiResponse(responseCode = "200", description = "API 호출 성공"),

                @ApiResponse(
                        responseCode = "404",
                        description = "잘못된 Method 요청, 존재하지 않는 API , 존재하지 않는 Data.",
                        content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "잘못 된 데이터 요청 및 유효성 검증 실패",
                        content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "데이터 등록 실패 및 전반적인 서버 에러 ",
                        content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                ),
        })
public @interface ApiDocumentResponseAnnotation {




}
