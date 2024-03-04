package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Boda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodaRepository extends JpaRepository<Boda, Long> {
}
