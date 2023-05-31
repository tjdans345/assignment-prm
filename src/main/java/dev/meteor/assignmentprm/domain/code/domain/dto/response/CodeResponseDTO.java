package dev.meteor.assignmentprm.domain.code.domain.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeResponseDTO {

    @Schema(description = "코드 인덱스", example = "1")
    private Long codeIdx;

    @Schema(description = "코드 uuid")
    private String codeUuid;

    @Schema(description = "코드 네임", example = "서울 대리점")
    private String codeName;

    @Schema(description = "코드 밸류 (영문 값)")
    private String codeValue;

    @Schema(description = "코드 설명", example = "이 코드그룹은 서울시의 대리점 그룹입니다.")
    private String codeDescription;

    @Schema(description = "코드 생성일")
    private String codeCreateDate;

    @Schema(description = "해당 코드가 속해있는 코드 그룹 DTO 객체")
    private CodeGroupResponseDTO codeGroupResponseDTO;

    @Builder
    public CodeResponseDTO(Long codeIdx, String codeUuid, String codeName, String codeValue,
                           String codeDescription, String codeCreateDate,
                           CodeGroupResponseDTO codeGroupResponseDTO) {
        this.codeIdx = codeIdx;
        this.codeUuid = codeUuid;
        this.codeName = codeName;
        this.codeValue = codeValue;
        this.codeDescription = codeDescription;
        this.codeCreateDate = codeCreateDate;
        this.codeGroupResponseDTO = codeGroupResponseDTO;
    }
}
