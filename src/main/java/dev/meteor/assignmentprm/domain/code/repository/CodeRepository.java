package dev.meteor.assignmentprm.domain.code.repository;

import dev.meteor.assignmentprm.domain.code.domain.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<CodeEntity, Long> {
}
