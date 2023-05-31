package dev.meteor.assignmentprm.domain.code.domain.entity;


import dev.meteor.assignmentprm.domain.code.domain.dto.response.CodeResponseDTO;
import dev.meteor.assignmentprm.domain.code.enums.CodeStatusEnum;
import dev.meteor.assignmentprm.global.common.domain.enity.BaseCreateAndUpdateTimeEntity;
import dev.meteor.assignmentprm.global.utils.StringUtils;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_common_code")
public class CodeEntity extends BaseCreateAndUpdateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; // 코드 인덱스

    private String uuid; // 코드 uuid

    private String name; // 코드 이름(한글명)

    private String description; // 코드에 대한 간략한 설명

    private String value; // 코드 값

    @ManyToOne
    @JoinColumn(name = "group_idx")
    private CodeGroupEntity group;

    private LocalDateTime deleteDate; // 삭제일

    private String deleteYn; // 삭제여부

    @Enumerated(EnumType.STRING)
    private CodeStatusEnum status; // 코드 상태

    @Builder
    public CodeEntity(Long idx, String uuid, String name,
                      String description, String value, CodeGroupEntity group,
                      LocalDateTime deleteDate, String deleteYn, CodeStatusEnum status) {
        this.idx = idx;
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.value = value;
        this.group = group;
        this.deleteDate = deleteDate;
        this.deleteYn = deleteYn;
        this.status = status;
    }

    /**
     * codeEntity To codeDTO
     * @return CodeResponseDTO
     */
    public CodeResponseDTO toCodeResponseDTO() {
        return CodeResponseDTO.builder()
                .codeIdx(idx)
                .codeUuid(uuid)
                .codeName(name)
                .codeValue(value)
                .codeDescription(description)
                .codeCreateDate(StringUtils.localDateTimeToString(getCreateDate()))
                .codeGroupResponseDTO(group.toCodeGroupResponseDTO())
                .build();
    }
}
