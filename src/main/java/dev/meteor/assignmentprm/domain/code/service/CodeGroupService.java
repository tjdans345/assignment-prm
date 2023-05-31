package dev.meteor.assignmentprm.domain.code.service;

import dev.meteor.assignmentprm.domain.code.domain.dto.request.CheckDuplicateNameRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.CreateCodeGroupRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.response.CodeGroupResponseDTO;
import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import dev.meteor.assignmentprm.domain.code.enums.CodeGroupStatusEnum;
import dev.meteor.assignmentprm.domain.code.repository.CodeGroupRepository;
import dev.meteor.assignmentprm.global.enum_package.error.ErrorEnum;
import dev.meteor.assignmentprm.global.error.CommonException.CommonException;
import dev.meteor.assignmentprm.global.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CodeGroupService {

    private final CodeGroupRepository codeGroupRepository;

    // 코드 그룹 리스트 조회
    public Page<CodeGroupEntity> getCodeGroupList(Pageable pageable) {
        Page<CodeGroupEntity> codeGroupPageList = codeGroupRepository.findAllByDeleteYnAndStatus(pageable, "N", CodeGroupStatusEnum.ACTIVE);

        if(codeGroupPageList == null || codeGroupPageList.isEmpty()) {
            throw new CommonException(ErrorEnum.EMPTY_LIST);
        }
        return codeGroupPageList;
    }
    // 코드 그룹 상세 조회
    public void getCodeGroupDetail(Long idx) {

    }

    /**
     * 코드 그룹 생성
     * @param createCodeGroupRequestDTO CreateCodeGroupRequestDTO
     * @return CodeGroupResponseDTO
     */
    @Transactional
    public CodeGroupResponseDTO createCodeGroup(CreateCodeGroupRequestDTO createCodeGroupRequestDTO) {

        CodeGroupEntity codeGroup = codeGroupRepository.save(createCodeGroupRequestDTO.toCodeGroupEntity());

        return codeGroup.toCodeGroupResponseDTO();

    }

    /**
     * 코드 그룹 이름 중복 조회 Method
     * @param checkDuplicateNameRequestDTO CheckDuplicateNameRequestDTO
     * @return boolean (통과여부 true : 통과)
     */
    public boolean checkDuplicateCodeGroupName( CheckDuplicateNameRequestDTO checkDuplicateNameRequestDTO) {

        // 데이터 유효성 검증 진행
        checkDataValid(checkDuplicateNameRequestDTO.getCheckName());

        // 중복체크 진행
        // AOP 로 리팩토링 진행 완료
//        codeGroupRepository.findByName(checkDuplicateNameRequestDTO.getCheckName()).ifPresent(
//                codeGroupEntity -> {throw new CommonException(
//                        ErrorEnum.ALREADY_DATA
//                );}
//        );
        return true;
    }

    /**
     * 중복 사용을 위한 Extracted Method
     * 데이터 Null, 공백, 빈 값 체크 진행
     * @param checkData String (체크 할 값)
     */
    private void checkDataValid(String checkData) {
        boolean checkResult = StringUtils.checkDataEmpty(checkData);
        if (checkResult) {
            throw new CommonException( ErrorEnum.EMPTY_REQUEST_DATA);
        }
    }


    // 코드 그룹 수정
    public void updateCodeGroup() {

    }

    // 코드 그룹 삭제
    public void deleteCodeGroup(Long idx) {

    }

}
