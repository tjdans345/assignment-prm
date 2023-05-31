package dev.meteor.assignmentprm.domain.code.domain.dto.request;

import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import dev.meteor.assignmentprm.global.enum_package.uuid.UuidTypeEnum;
import dev.meteor.assignmentprm.global.utils.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCodeRequestDTO {

    @Schema(description = "코드 이름")
    @NotBlank(message = "코드 이름을 입력해주세요.")
    private String codeName;

    @Schema(description = "코드 그룹 인덱스")
    @NotNull(message = "코드 그룹번호를 입력해주세요.")
    private Long codeGroupIdx;

    @Builder
    public CreateCodeRequestDTO(String codeName, Long codeGroupIdx) {
        this.codeName = codeName;
        this.codeGroupIdx = codeGroupIdx;
    }

    public CodeGroupEntity toCodeGroupEntity() {
        return CodeGroupEntity.builder()
                .uuid(StringUtils.createUUID(UuidTypeEnum.CODE_GROUP))
                .name(codeName)
                .build();
    }
}
