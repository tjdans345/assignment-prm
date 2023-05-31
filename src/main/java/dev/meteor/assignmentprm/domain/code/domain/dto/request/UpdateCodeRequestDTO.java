package dev.meteor.assignmentprm.domain.code.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCodeRequestDTO {

    @Schema(description = "코드 인덱스")
    private Long codeIdx;

    @Schema(description = "코드 그룹 번호", example = "16")
    @NotNull(message = "코드 그룹 번호를 입력해주세요.")
    private Long codeGroupIdx;

    @Schema(description = "코드 이름")
    @NotBlank(message = "코드 이름을 입력해주세요.")
    private String codeName;

    @Schema(description = "코드 설명")
    @NotBlank(message = "코드 설명을 입력해주세요.")
    @Size(max = 300, message = "최대 300자 까지 입력 가능합니다. ")
    private String codeDescription;

    @Schema(description = "코드 그룹 상태 변경", example = "ACTIVE, DISABLE")
    @NotBlank(message = "코드 그룹 상태를 입력해주세요.")
    private String codeStatus;

}
