package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Boda;
import org.iesvdm.api_rest.domain.Menu;
import org.iesvdm.api_rest.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    public List<Menu> findMenusByNombreContainingIgnoreCase(String nombre);

    public List<Menu> findMenusByPrimeroContainingIgnoreCase(String primero);

    public List<Menu> findMenusBySegundoContainingIgnoreCase(String segundo);

    public List<Menu> findMenusByPostreContainingIgnoreCase(String postre);
}
