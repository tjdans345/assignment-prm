package dev.meteor.assignmentprm.domain.code.domain.dto.request;

import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import dev.meteor.assignmentprm.domain.code.enums.CodeGroupStatusEnum;
import dev.meteor.assignmentprm.global.enum_package.uuid.UuidTypeEnum;
import dev.meteor.assignmentprm.global.utils.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCodeGroupRequestDTO {

    @Schema(description = "코드 그룹 이름")
    @NotBlank(message = "코드 그룹 이름을 입력해주세요.")
    private String codeGroupName;


    @Schema(description = "코드 그룹 설명")
    @NotBlank(message = "코드 그룹 설명을 입력해주세요.")
    @Size(max = 300, message = "최대 300자 까지 입력 가능합니다. ")
    private String codeGroupDescription;


    @Builder
    public CreateCodeGroupRequestDTO(String codeGroupName, String codeGroupDescription) {
        this.codeGroupName = codeGroupName;
        this.codeGroupDescription = codeGroupDescription;
    }

    public CodeGroupEntity toCodeGroupEntity() {
        return CodeGroupEntity.builder()
                .uuid(StringUtils.createUUID(UuidTypeEnum.CODE_GROUP))
                .name(codeGroupName)
                .description(codeGroupDescription)
                .deleteYn("N")
                .status(CodeGroupStatusEnum.ACTIVE)
                .build();
    }
}
