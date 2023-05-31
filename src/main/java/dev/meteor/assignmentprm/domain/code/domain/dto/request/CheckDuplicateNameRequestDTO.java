package dev.meteor.assignmentprm.domain.code.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CheckDuplicateNameRequestDTO {

    @Schema(description = "중복 조회 할 이름", example = "서울시 대리점")
    @NotBlank(message = "중복 확인을 진행 할 이름을 입력해주세요.")
    private String checkName;

}
