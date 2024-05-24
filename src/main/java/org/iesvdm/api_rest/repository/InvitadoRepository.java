package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Invitado;
import org.iesvdm.api_rest.domain.Regalo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitadoRepository extends JpaRepository <Invitado, Long> {

    public Page<Invitado> findInvitadoByNombreContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefonoContainingIgnoreCase
            (String nombre, String apellido1, String apellido2, String email, String telefono, Pageable pageable);
}
