package dev.meteor.assignmentprm.domain.code.service;

import dev.meteor.assignmentprm.domain.code.repository.CodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CodGroupService {

    private final CodeGroupRepository codeGroupRepository;

    // 코드 그룹 리스트 조회

    // 코드 그룹 상세 조회

    // 코드 그룹 생성

    // 코드 그룹 이름 중복 조회

    // 코드 그룹 수정

    // 코드 그룹 삭제


}
