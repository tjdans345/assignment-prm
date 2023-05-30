package dev.meteor.assignmentprm.domain.code.service;

import dev.meteor.assignmentprm.domain.code.domain.dto.request.CheckDuplicateNameRequestDTO;
import dev.meteor.assignmentprm.domain.code.domain.dto.request.CreateCodeRequestDTO;
import dev.meteor.assignmentprm.domain.code.repository.CodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CodeGroupService {

    private final CodeGroupRepository codeGroupRepository;

    // 코드 그룹 리스트 조회
    public void getCodeGroupList(Pageable pageable) {

    }
    // 코드 그룹 상세 조회
    public void getCodeGroupDetail(Long idx) {

    }

    // 코드 그룹 생성
    public void createCodeGroup(CreateCodeRequestDTO createCodeRequestDTO) {




    }

    // 코드 그룹 이름 중복 조회
    public void checkDuplicateCodeGroupName( CheckDuplicateNameRequestDTO checkDuplicateNameRequestDTO) {

    }


    // 코드 그룹 수정
    public void updateCodeGroup() {

    }

    // 코드 그룹 삭제
    public void deleteCodeGroup(Long idx) {

    }

}
