package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Invitado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitadoRepository extends JpaRepository <Invitado, Long> {
}
