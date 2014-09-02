package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.State;

public interface StateDAO extends JpaRepository<State, Long> {

	State findByCode(String code);
}
