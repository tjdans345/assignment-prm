package dev.meteor.assignmentprm.domain.code.repository;

import dev.meteor.assignmentprm.domain.code.domain.entity.CodeEntity;
import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import dev.meteor.assignmentprm.domain.code.enums.CodeStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<CodeEntity, Long> {

    Page<CodeEntity> findAllByDeleteYnAndStatus(Pageable pageable, String deleteYn, CodeStatusEnum status);
    Page<CodeEntity> findAllByDeleteYnAndStatusAndGroupIdx(Pageable pageable, String deleteYn, CodeStatusEnum status, Long codeGroupIdx);
    Optional<CodeEntity> findByIdxAndStatus(Long idx, CodeStatusEnum status);
    Optional<CodeEntity> findByName(String codeName);
}
