package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    // Metodo que filtra por nombre, primero, segundo y postre y recibe 4 parametros
    // En su ejecución usa la misma cadena pasada como parametro en el endpoint para los 4 campos del método
    // consiguiendo un filtrado de la coleccion por todos sus campos.
    public List<Menu> findMenusByNameContainingIgnoreCaseOrStarterContainingIgnoreCaseOrMainCourseContainingIgnoreCaseOrDessertContainingIgnoreCase
    (String name, String starter, String main, String dessert);

    public Page<Menu> findByWedding_Id(Long id, Pageable pageable);

    public Page<Menu> findMenusByNameContainingIgnoreCaseOrStarterContainingIgnoreCaseOrMainCourseContainingIgnoreCaseOrDessertContainingIgnoreCaseAndWedding_Id(String name, String starter, String main, String dessert, Long id, Pageable pageable);
}
