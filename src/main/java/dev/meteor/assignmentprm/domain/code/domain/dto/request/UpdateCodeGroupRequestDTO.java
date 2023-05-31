package dev.meteor.assignmentprm.domain.code.domain.dto.request;

import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import dev.meteor.assignmentprm.domain.code.enums.CodeGroupStatusEnum;
import dev.meteor.assignmentprm.global.enum_package.uuid.UuidTypeEnum;
import dev.meteor.assignmentprm.global.utils.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCodeGroupRequestDTO {

    @Schema(description = "코드 그룹 인덱스")
    private Long codeGroupIdx;

    @Schema(description = "코드 그룹 이름")
    @NotBlank(message = "코드 그룹 이름을 입력해주세요.")
    private String codeGroupName;


    @Schema(description = "코드 그룹 설명")
    @NotBlank(message = "코드 그룹 설명을 입력해주세요.")
    @Size(max = 300, message = "최대 300자 까지 입력 가능합니다. ")
    private String codeGroupDescription;

    @Schema(description = "코드 그룹 상태 변경", example = "ACTIVE, DISABLE")
    @NotBlank(message = "코드 그룹 상태를 입력해주세요.")
    private String codeGroupStatus;

}
