package org.ucema.sgsp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.RatePlan;

public interface RatePlanDAO extends JpaRepository<RatePlan, Long> {

	RatePlan findByCode(String code);
	
	List<RatePlan> findByCode(List<String> codes);
}
