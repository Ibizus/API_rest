package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.ERol;
import org.iesvdm.api_rest.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRol(ERol rol);
}