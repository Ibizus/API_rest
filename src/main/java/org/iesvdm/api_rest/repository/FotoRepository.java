package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository <Foto, Long> {
}
