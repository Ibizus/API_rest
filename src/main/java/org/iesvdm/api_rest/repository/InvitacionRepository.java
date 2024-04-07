package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Invitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitacionRepository extends JpaRepository <Invitacion, Long> {
}
