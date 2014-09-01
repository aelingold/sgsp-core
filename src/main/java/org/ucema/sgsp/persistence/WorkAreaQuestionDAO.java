package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.WorkAreaQuestion;

public interface WorkAreaQuestionDAO extends JpaRepository<WorkAreaQuestion, Long> {
}
