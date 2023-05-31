package dev.meteor.assignmentprm;

import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import dev.meteor.assignmentprm.domain.code.enums.CodeGroupStatusEnum;
import dev.meteor.assignmentprm.domain.code.repository.CodeGroupRepository;
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
public class CodeGroupRepositoryTest {

    @Autowired
    private CodeGroupRepository codeGroupRepository;
    private final String newCodeName = "새로운 코드 그룹";

    private final String newDescription = "서울 지역의 대리점에서 사용되는 공통코드들을 관리하는 코드그룹 입니다.";

    private final CodeGroupStatusEnum codeGroupStatus = CodeGroupStatusEnum.ACTIVE;

    @Test
    @DisplayName("데이터 저장 확인")
    void createCodeGroup() {
        String newUuid = createUuid();
        CodeGroupEntity codeGroup = saveGroup(newUuid, "N");

        Assertions.assertEquals(codeGroup.getName(), newCodeName);
        Assertions.assertEquals(codeGroup.getStatus(), codeGroupStatus);
        Assertions.assertNull(codeGroup.getCodeEntityList());
        Assertions.assertNull(codeGroup.getDeleteDate());
        Assertions.assertEquals(codeGroup.getDeleteYn(), "N");
        Assertions.assertEquals(codeGroup.getUuid(), newUuid);
        Assertions.assertEquals(codeGroup.getDescription(), newDescription);

    }

    @Test
    @DisplayName("전체 내역 조회 + deleteYn(N)")
    void findAll() {
        String uuid = createUuid();
        CodeGroupEntity codeGroup1 = saveGroup(uuid, "N");
        List<CodeGroupEntity> codeGroups = codeGroupRepository.findAll();

        Assertions.assertNotEquals(codeGroups.size(), 0);

    }


    @Test
    @DisplayName("ID 조회")
    void findById() {
        String uuid = createUuid();
        CodeGroupEntity codeGroup = saveGroup(uuid, "N");
        Optional<CodeGroupEntity> optional = codeGroupRepository.findById(codeGroup.getIdx());

        Assertions.assertTrue(optional.isPresent());

    }

    @Test
    @DisplayName("ID 조회 + Status 값")
    void findIdxAndStatus() {
        String uuid = createUuid();
        CodeGroupEntity codeGroup = saveGroup(uuid, "N");
        Optional<CodeGroupEntity> optional = codeGroupRepository.findByIdxAndStatus(codeGroup.getIdx(), codeGroupStatus);

        Assertions.assertTrue(optional.isPresent());
    }

    @Test
    @DisplayName("데이터 업데이트")
    void updateCodeGroup() {
        final String newName = "변경된 그룹이름";
        String uuid = createUuid();
        CodeGroupEntity codeGroup = saveGroup(uuid, "N");

        codeGroup.setName(newName);
        codeGroup.setStatus(CodeGroupStatusEnum.DISABLE);
        CodeGroupEntity updateGroup = codeGroupRepository.save(codeGroup);

        Assertions.assertEquals(codeGroup.getIdx(), updateGroup.getIdx());
        Assertions.assertEquals(updateGroup.getName(), newName);
        Assertions.assertEquals(updateGroup.getStatus(), CodeGroupStatusEnum.DISABLE);


    }


    private CodeGroupEntity saveGroup(String uuid, String deleteYn){
        return codeGroupRepository.save(CodeGroupEntity.builder()
                .idx(null)
                .uuid(uuid)
                .name(newCodeName)
                .status(CodeGroupStatusEnum.ACTIVE)
                .deleteYn(deleteYn)
                .description(newDescription)
                .build());
    }

    private String createUuid() {
        return StringUtils.createUUID(UuidTypeEnum.CODE_GROUP);
    }



}
