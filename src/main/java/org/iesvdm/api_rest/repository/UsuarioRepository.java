package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Page<Usuario> findUsuariosByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    public Page<Usuario> findUsuariosByApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCase(String apellido1, String apellido2, Pageable pageable);

}
