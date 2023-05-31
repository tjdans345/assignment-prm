package dev.meteor.assignmentprm.domain.code.repository;

import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import dev.meteor.assignmentprm.domain.code.enums.CodeGroupStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CodeGroupRepository extends JpaRepository<CodeGroupEntity, Long> {

    Page<CodeGroupEntity> findAllByDeleteYnAndStatus(Pageable pageable, String deleteYn, CodeGroupStatusEnum status);

    Optional<CodeGroupEntity> findByName(String name);
    Optional<CodeGroupEntity> findByIdxAndStatus(Long idx, CodeGroupStatusEnum status);
}
