package dev.meteor.assignmentprm.domain.code.domain.entity;

import dev.meteor.assignmentprm.domain.code.enums.CodeGroupStatusEnum;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_common_code_group")
public class CodeGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; // 코드 그룹 인덱스

    private String name; // 코드 그룹 이름

    private String description; // 코드 그룹에 대한 간략한 설명

    private LocalDateTime deleteDate; // 삭제일

    private String deleteYn; // 삭제여부

    @Enumerated(EnumType.STRING)
    private CodeGroupStatusEnum status; // 코드 그룹 상태

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "group_idx")
    private List<CodeEntity> codeEntityList;

    @Builder
    public CodeGroupEntity(Long idx, String name, String description,
                           LocalDateTime deleteDate, String deleteYn, CodeGroupStatusEnum status, List<CodeEntity> codeEntityList) {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.deleteDate = deleteDate;
        this.deleteYn = deleteYn;
        this.status = status;
        this.codeEntityList = codeEntityList;
    }
}
