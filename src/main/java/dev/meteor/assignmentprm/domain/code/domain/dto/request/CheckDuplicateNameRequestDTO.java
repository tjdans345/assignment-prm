package dev.meteor.assignmentprm.domain.code.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CheckDuplicateNameRequestDTO {

    @NotBlank(message = "중복 확인을 진행 할 이름을 입력해주세요.")
    private String checkName;

}
