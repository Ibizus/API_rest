package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository <Tarea, Long> {
}
