package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Boda;
import org.iesvdm.api_rest.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodaRepository extends JpaRepository<Boda, Long> {

    public Boda findBodaByUsuario(Usuario usuario);
}
