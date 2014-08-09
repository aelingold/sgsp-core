package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.WorkAreaItem;

public interface WorkAreaItemDAO extends JpaRepository<WorkAreaItem, Long> {

}
