package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Regalo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegaloRepository extends JpaRepository <Regalo, Long> {

    public Page<Regalo> findRegaloByNombreContainingIgnoreCase(String name, Pageable pageable);
}
