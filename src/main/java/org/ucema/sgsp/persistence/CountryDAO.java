package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.Country;

public interface CountryDAO extends JpaRepository<Country, Long> {

}
