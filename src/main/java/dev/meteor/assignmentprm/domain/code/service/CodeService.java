package dev.meteor.assignmentprm.domain.code.service;

import dev.meteor.assignmentprm.domain.code.domain.dto.request.CheckDuplicateNameRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.CreateCodeRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.UpdateCodeRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.response.CodeResponseDTO;
import dev.meteor.assignmentprm.domain.code.domain.entity.CodeEntity;
import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import dev.meteor.assignmentprm.domain.code.enums.CodeGroupStatusEnum;
import dev.meteor.assignmentprm.domain.code.enums.CodeStatusEnum;
import dev.meteor.assignmentprm.domain.code.repository.CodeGroupRepository;
import dev.meteor.assignmentprm.domain.code.repository.CodeRepository;
import dev.meteor.assignmentprm.global.aop.annotation.ApiDocumentResponseAnnotation;
import dev.meteor.assignmentprm.global.enum_package.error.ErrorEnum;
import dev.meteor.assignmentprm.global.error.CommonException.CommonException;
import dev.meteor.assignmentprm.global.utils.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;
    private final CodeGroupRepository codeGroupRepository;


    /**
     * 코드 리스트 조회
     * Entity To Dto Converter 진행 후 리턴
     *
     * @param pageable Pageable
     * @return Page<CodeResponseDTO>
     */
    public Page<CodeResponseDTO> getCodeList(Pageable pageable) {
        Page<CodeEntity> codePageList = codeRepository.findAllByDeleteYnAndStatus(pageable, "N", CodeStatusEnum.ACTIVE);

        if (codePageList == null || codePageList.isEmpty()) {
            throw new CommonException(ErrorEnum.EMPTY_LIST);
        }
        return codePageList.map(CodeEntity::toCodeResponseDTO);
    }


    /**
     * 특정 코드 그룹을 가지고 있는 코드 리스트 조회
     * @param pageable Pageable
     * @param codeGroupIdx Long (코드 그룹 인덱스)
     * @return Page<CodeResponseDTO>
     */
    public Page<CodeResponseDTO> getCodeContainCodeGroup(Pageable pageable, Long codeGroupIdx) {

        Optional<CodeGroupEntity> codeGroup = codeGroupRepository.findById(codeGroupIdx);

        if(codeGroup.isEmpty()) {
            throw new CommonException(ErrorEnum.NOT_FOUND_DATA);
        }

        Page<CodeEntity> codeContainCodeGroupPageList = codeRepository.findAllByDeleteYnAndStatusAndGroupIdx(pageable,
                "N", CodeStatusEnum.ACTIVE, codeGroup.get().getIdx());

        if (codeContainCodeGroupPageList == null || codeContainCodeGroupPageList.isEmpty()) {
            throw new CommonException(ErrorEnum.EMPTY_LIST);
        }

        return codeContainCodeGroupPageList.map(CodeEntity::toCodeResponseDTO);
    }

    /**
     * 코드 상세 조회
     * @param idx Long (코드 Idx)
     * @return CodeGroupResponseDTO
     */
    public CodeResponseDTO getCodeDetail(Long idx) {
        Optional<CodeEntity> codeOptional = codeRepository.findById(idx);
        if(codeOptional.isEmpty()) {
            throw new CommonException(ErrorEnum.NOT_FOUND_DATA);
        }

        return codeOptional.get().toCodeResponseDTO();
    }

    /**
     * 코드 생성
     * @param createCodeRequestDTO CreateCodeRequestDTO
     * @return CodeResponseDTO
     */
    @Transactional
    public CodeResponseDTO createCode(CreateCodeRequestDTO createCodeRequestDTO) {

        CodeGroupEntity codeGroup = checkExistCodeGroup(createCodeRequestDTO.getCodeGroupIdx());

        CodeEntity codeEntity = createCodeRequestDTO.toCodeEntity(codeGroup);
        return codeRepository.save(codeEntity).toCodeResponseDTO();

    }


    /**
     * 코드 그룹 이름 중복 조회 Method
     * 중복 조회 비지니스 로직은 ValidationAdvice 에 있습니다.
     *
     * @param checkDuplicateNameRequestDTO CheckDuplicateNameRequestDTO
     * @return boolean (통과여부 true : 통과)
     */
    public boolean checkDuplicateCodeName(CheckDuplicateNameRequestDTO checkDuplicateNameRequestDTO) {
        // 데이터 유효성 검증 진행
        checkDataValid(checkDuplicateNameRequestDTO.getCheckName());
        return true;
    }

    /**
     * 중복 사용을 위한 Extracted Method
     * 데이터 Null, 공백, 빈 값 체크 진행
     *
     * @param checkData String (체크 할 값)
     */
    private void checkDataValid(String checkData) {
        boolean isNullOrEmpty = StringUtils.checkDataNullOrEmpty(checkData);
        if (isNullOrEmpty) {
            throw new CommonException(ErrorEnum.EMPTY_REQUEST_DATA);
        }
    }

    /**
     * 코드 그룹 수정
     *
     * @param updateCodeRequestDTO UpdateCodeRequestDTO
     * @return CodeResponseDTO
     */
    @Transactional
    public CodeResponseDTO updateCode(UpdateCodeRequestDTO updateCodeRequestDTO) {

        CodeGroupEntity codeGroup = checkExistCodeGroup(updateCodeRequestDTO.getCodeGroupIdx());

        Optional<CodeEntity> codeOptional = codeRepository.findById(updateCodeRequestDTO.getCodeIdx());

        if (codeOptional.isEmpty()) {
            throw new CommonException(ErrorEnum.NOT_FOUND_DATA);
        }

        CodeEntity code = codeOptional.get();

        // 삭제 여부 체크 삭제 된 데이터는 데이터 값 수정 안하기 위한 목적
        checkAlreadyDeleteData(code);

        code.setName(updateCodeRequestDTO.getCodeName());
        code.setDescription(updateCodeRequestDTO.getCodeDescription());
        code.setGroup(codeGroup);
        code.setStatus(CodeStatusEnum.valueOf(updateCodeRequestDTO.getCodeStatus()));

        return codeRepository.save(code).toCodeResponseDTO();


    }

    /**
     * 존재하는 코드 그룹인지 확인 후 없으면 Exception 발생
     * 코드 그룹이 존재하면 CodeGroupEntity 반환
     *
     * @param codeGroupIdx Long ( 코드 그룹 Idx )
     * @return CodeGroupEntity
     */
    private CodeGroupEntity checkExistCodeGroup(Long codeGroupIdx) {
        Optional<CodeGroupEntity> codeGroupOptional = codeGroupRepository.findByIdxAndStatusAndDeleteYn(
                codeGroupIdx,
                CodeGroupStatusEnum.ACTIVE,
                "N"
        );
        // 코드 그룹 존재여부 검증
        if (codeGroupOptional.isEmpty()) {
            throw new CommonException(ErrorEnum.NOT_FOUND_DATA);
        }
        return codeGroupOptional.get();
    }

    /**
     * 코드 삭제
     *
     * @param idx Long (코드 Idx)
     * @return String ( 결과 메시지 )
     */
    public String deleteCode(Long idx) {

        if (idx == null) {
            throw new CommonException(ErrorEnum.EMPTY_REQUEST_DATA);
        }

        Optional<CodeEntity> codeOptional = codeRepository.findById(idx);
        if (codeOptional.isEmpty()) {
            throw new CommonException(ErrorEnum.NOT_FOUND_DATA);
        }
        CodeEntity code = codeOptional.get();

        // 데이터 삭제 여부 체크 ( 이미 삭제 된 데이터면 이 후 로직 실행 안시키기 위함 )
        checkAlreadyDeleteData(code);

        code.setDeleteYn("Y");
        code.setDeleteDate(LocalDateTime.now());

        codeRepository.save(code);

        return "데이터 삭제에 성공하였습니다.";

    }

    /**
     * 코드 데이터 삭제 여부 체크 Method
     *
     * @param codeEntity CodeEntity
     */
    private void checkAlreadyDeleteData(CodeEntity codeEntity) {
        if ("Y".equals(codeEntity.getDeleteYn())) {
            throw new CommonException(ErrorEnum.ALREADY_DELETE_DATA);
        }
    }


}
