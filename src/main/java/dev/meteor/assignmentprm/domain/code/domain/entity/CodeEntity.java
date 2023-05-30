package dev.meteor.assignmentprm.domain.code.domain.entity;


import dev.meteor.assignmentprm.domain.code.enums.CodeGroupEnum;
import dev.meteor.assignmentprm.global.common.domain.enity.BaseCreateAndUpdateTimeEntity;
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

    private String name; // 코드 이름(한글명)

    private String value; // 코드 값

    // 코드 그룹 인덱스
    // 필요에 의해 연관관계 맺어도 상관없지만 코드 데이터 자체로 그룹 데이터를 필요로 하지 않는 다는 가정하에 개발 진행.
    private Long group_idx;

    private LocalDateTime deleteDate; // 삭제일

    private String deleteYn; // 삭제여부

    private String useYn; // 사용여부

    @Builder
    public CodeEntity(Long idx, String name, Long group_idx,
                      LocalDateTime deleteDate, String deleteYn, String useYn) {
        this.idx = idx;
        this.name = name;
        this.group_idx = group_idx;
        this.deleteDate = deleteDate;
        this.deleteYn = deleteYn;
        this.useYn = useYn;
    }

}
