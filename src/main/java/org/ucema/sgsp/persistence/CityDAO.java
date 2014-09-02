package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.City;

public interface CityDAO extends JpaRepository<City, Long> {

	City findByCode(String code);
}
