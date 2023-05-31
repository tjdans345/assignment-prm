package dev.meteor.assignmentprm.domain.code.domain.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeGroupResponseDTO {

    @Schema(description = "코드그룹 인덱스", example = "1")
    private Long codeGroupIdx;

    @Schema(description = "코드그룹 uuid")
    private String codeGroupUuid;


    @Schema(description = "코드그룹 네임", example = "서울 대리점")
    private String codeGroupName;


    @Schema(description = "코드그룹 설명", example = "이 코드그룹은 서울시의 대리점 그룹입니다.")
    private String codeGroupDescription;


    @Schema(description = "코드그룹 생성일")
    private String codeGroupCreateDate;

    @Builder
    public CodeGroupResponseDTO(Long codeGroupIdx, String codeGroupUuid, String codeGroupName, String codeGroupDescription, String codeGroupCreateDate) {
        this.codeGroupIdx = codeGroupIdx;
        this.codeGroupUuid = codeGroupUuid;
        this.codeGroupName = codeGroupName;
        this.codeGroupDescription = codeGroupDescription;
        this.codeGroupCreateDate = codeGroupCreateDate;
    }
}
