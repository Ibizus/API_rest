package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository <Evento, Long> {
}
