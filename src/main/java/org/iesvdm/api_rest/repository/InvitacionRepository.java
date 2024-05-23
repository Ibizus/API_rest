package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Invitacion;
import org.iesvdm.api_rest.domain.Regalo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitacionRepository extends JpaRepository <Invitacion, Long> {

    public Page<Invitacion> findInvitacionByNombreInvitadoContainingIgnoreCase(String name, Pageable pageable);
}
