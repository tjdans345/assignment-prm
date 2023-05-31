package dev.meteor.assignmentprm.domain.code.service;

import dev.meteor.assignmentprm.domain.code.domain.dto.request.CheckDuplicateNameRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.CreateCodeGroupRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.UpdateCodeGroupRequestDTO;
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

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CodeGroupService {

    private final CodeGroupRepository codeGroupRepository;


    /**
     * 코드 그룹 리스트 조회
     * Entity To Dto Converter 진행 후 리턴
     * @param pageable Pageable
     * @return Page<CodeGroupResponseDTO>
     */
    public Page<CodeGroupResponseDTO> getCodeGroupList(Pageable pageable) {
        Page<CodeGroupEntity> codeGroupPageList = codeGroupRepository.findAllByDeleteYnAndStatus(pageable, "N", CodeGroupStatusEnum.ACTIVE);

        if(codeGroupPageList == null || codeGroupPageList.isEmpty()) {
            throw new CommonException(ErrorEnum.EMPTY_LIST);
        }
        return codeGroupPageList.map(CodeGroupEntity::toCodeGroupResponseDTO);
    }
    // 코드 그룹 상세 조회
    public CodeGroupResponseDTO getCodeGroupDetail(Long idx) {

        Optional<CodeGroupEntity> codeGroupOptional = codeGroupRepository.findById(idx);

        if(codeGroupOptional.isEmpty()) {
            throw new CommonException(ErrorEnum.NOT_FOUND_DATA);
        }

        return codeGroupOptional.get().toCodeGroupResponseDTO();

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

    /**
     * 코드 그룹 수정
     * @param updateCodeGroupRequestDTO UpdateCodeGroupRequestDTO
     * @return CodeGroupResponseDTO
     */
    @Transactional
    public CodeGroupResponseDTO updateCodeGroup(UpdateCodeGroupRequestDTO updateCodeGroupRequestDTO) {

        Optional<CodeGroupEntity> codeGroupOptional = codeGroupRepository.findById(updateCodeGroupRequestDTO.getCodeGroupIdx());

        if(codeGroupOptional.isEmpty()) {
            throw new CommonException(ErrorEnum.NOT_FOUND_DATA);
        }
        CodeGroupEntity codeGroup = codeGroupOptional.get();

        // 삭제 여부 체크 삭제 된 데이터는 데이터 값 수정 안하기 위한 목적
        checkDeleteData(codeGroup);

        codeGroup.setName(updateCodeGroupRequestDTO.getCodeGroupName());
        codeGroup.setStatus(CodeGroupStatusEnum.valueOf(updateCodeGroupRequestDTO.getCodeGroupStatus()));
        codeGroup.setDescription(updateCodeGroupRequestDTO.getCodeGroupDescription());

        // 더티 체킹을 이용할 수 있지만 데이터 검증성 확보 및 영속성 업데이트를 위한 save 이용
        CodeGroupEntity updateCodeGroup = codeGroupRepository.save(codeGroup);
        return updateCodeGroup.toCodeGroupResponseDTO();
    }

    // 코드 그룹 삭제
    @Transactional
    public String deleteCodeGroup(Long idx) {

        Optional<CodeGroupEntity> codeGroupOptional = codeGroupRepository.findById(idx);
        if(codeGroupOptional.isEmpty()) {
            throw new CommonException(ErrorEnum.NOT_FOUND_DATA);
        }
        CodeGroupEntity codeGroup = codeGroupOptional.get();

        // 데이터 삭제 여부 체크 ( 이미 삭제 된 데이터면 이 후 로직 실행 안시키기 위함 )
        checkDeleteData(codeGroup);

        // TODO 테스트 진행해봐야 함 (영속성)
        if(codeGroup.getCodeEntityList() != null && !codeGroup.getCodeEntityList().isEmpty()) {
            codeGroup.setCodeEntityList(codeGroup.getCodeEntityList().stream().peek(
                    codeEntity -> {
                        codeEntity.setDeleteYn("Y");
                        codeEntity.setDeleteDate(LocalDateTime.now());
                    }
            ).collect(Collectors.toList()));
        }

        codeGroup.setDeleteYn("Y");
        codeGroup.setDeleteDate(LocalDateTime.now());

        codeGroupRepository.save(codeGroup);

        return "삭제 처리에 성공하였습니다.";

    }

    /**
     * 코드 그룹 데이터 삭제 여부 체크 Method
     * @param codeGroup CodeGroupEntity
     */
    private void checkDeleteData(CodeGroupEntity codeGroup) {
        if("Y".equals(codeGroup.getDeleteYn())) {
            throw new CommonException(ErrorEnum.ALREADY_DELETE_DATA);
        }
    }

}
