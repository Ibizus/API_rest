package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findUsuariosByNombreContainsIgnoreCase(String nombre, Pageable pageable);
}
