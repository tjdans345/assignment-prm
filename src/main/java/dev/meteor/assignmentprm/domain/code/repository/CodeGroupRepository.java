package dev.meteor.assignmentprm.domain.code.repository;

import dev.meteor.assignmentprm.domain.code.domain.entity.CodeGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeGroupRepository extends JpaRepository<CodeGroupEntity, Long> {
}
