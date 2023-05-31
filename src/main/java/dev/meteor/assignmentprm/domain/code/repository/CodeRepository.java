package dev.meteor.assignmentprm.domain.code.repository;

import dev.meteor.assignmentprm.domain.code.domain.entity.CodeEntity;
import dev.meteor.assignmentprm.domain.code.enums.CodeStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<CodeEntity, Long> {

    Optional<CodeEntity> findByIdxAndStatus(Long idx, CodeStatusEnum status);
}
