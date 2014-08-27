package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.WorkArea;

public interface WorkAreaDAO extends JpaRepository<WorkArea, Long> {

	WorkArea findByCode(String code);
}
