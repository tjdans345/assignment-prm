package dev.meteor.assignmentprm;


import dev.meteor.assignmentprm.domain.code.domain.entity.CodeEntity;
import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import dev.meteor.assignmentprm.domain.code.enums.CodeGroupStatusEnum;
import dev.meteor.assignmentprm.domain.code.enums.CodeStatusEnum;
import dev.meteor.assignmentprm.domain.code.repository.CodeGroupRepository;
import dev.meteor.assignmentprm.domain.code.repository.CodeRepository;
import dev.meteor.assignmentprm.global.enum_package.uuid.UuidTypeEnum;
import dev.meteor.assignmentprm.global.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CodeRepositoryTest {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private CodeGroupRepository codeGroupRepository;

    private final String newCodeName = "새로운 코드";

    private final String newDescription = "서울 관악구 1번 대리점";

    private final CodeStatusEnum codeStatus = CodeStatusEnum.ACTIVE;

    @Test
    @DisplayName("데이터 저장 확인")
    void createCodeGroup() {
        String newUuid = createUuid();
        CodeEntity codeGroup = saveGroup(newUuid, "N");

        Assertions.assertEquals(codeGroup.getName(), newCodeName);
        Assertions.assertEquals(codeGroup.getStatus(), codeStatus);
        Assertions.assertNull(codeGroup.getDeleteDate());
        Assertions.assertEquals(codeGroup.getDeleteYn(), "N");
        Assertions.assertEquals(codeGroup.getUuid(), newUuid);
        Assertions.assertEquals(codeGroup.getDescription(), newDescription);

    }

    @Test
    @DisplayName("전체 내역 조회 + deleteYn(N)")
    void findAll() {
        String uuid = createUuid();
        CodeEntity codeGroup = saveGroup(uuid, "N");
        List<CodeEntity> codeGroups = codeRepository.findAll();

        Assertions.assertNotEquals(codeGroups.size(), 0);

    }


    @Test
    @DisplayName("ID 조회")
    void findById() {
        String uuid = createUuid();
        CodeEntity codeGroup = saveGroup(uuid, "N");

        Optional<CodeEntity> optional = codeRepository.findById(codeGroup.getIdx());

        Assertions.assertTrue(optional.isPresent());

    }

    @Test
    @DisplayName("ID 조회 + Status 값")
    void findIdxAndStatus() {
        String uuid = createUuid();
        CodeEntity codeGroup = saveGroup(uuid, "N");
        Optional<CodeEntity> optional = codeRepository.findByIdxAndStatus(codeGroup.getIdx(), codeStatus);

        Assertions.assertTrue(optional.isPresent());
    }

    @Test
    @DisplayName("데이터 업데이트")
    void updateCodeGroup() {
        final String newName = "변경된 그룹이름";
        String uuid = createUuid();
        CodeEntity code = saveGroup(uuid, "N");

        code.setName(newName);
        code.setStatus(CodeStatusEnum.DISABLE);
        CodeEntity updateGroup = codeRepository.save(code);

        Assertions.assertEquals(code.getIdx(), updateGroup.getIdx());
        Assertions.assertEquals(updateGroup.getName(), newName);
        Assertions.assertEquals(updateGroup.getStatus(), CodeStatusEnum.DISABLE);

    }

    private CodeEntity saveGroup(String uuid, String deleteYn) {

        CodeGroupEntity codeGroup = saveCodeGroup();

        return codeRepository.save(CodeEntity.builder()
                .idx(null)
                .uuid(uuid)
                .value(createCodeValue())
                .name(newCodeName)
                .status(CodeStatusEnum.ACTIVE)
                .deleteYn(deleteYn)
                .description(newDescription)
                .group_idx(codeGroup.getIdx())
                .build());
    }

    private CodeGroupEntity saveCodeGroup() {
        return codeGroupRepository.save(CodeGroupEntity.builder()
                .idx(null)
                .uuid(StringUtils.createUUID(UuidTypeEnum.CODE_GROUP))
                .name(newCodeName)
                .status(CodeGroupStatusEnum.ACTIVE)
                .deleteYn("N")
                .description(newDescription)
                .build());
    }

    private String createUuid() {
        return StringUtils.createUUID(UuidTypeEnum.CODE);
    }

    private String createCodeValue() {
        return StringUtils.createCodeValue();
    }

}
