package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Boda;
import org.iesvdm.api_rest.domain.Menu;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.BodaRepository;
import org.iesvdm.api_rest.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {

    @Autowired
    BodaRepository bodaRepository; //¿NECESARIO?
    @Autowired
    MenuRepository menuRepository;

    public List<Menu> all(){return this.menuRepository.findAll();}

    public List<Menu> filter(Optional<String> wordOptional){

        Set<Menu> encontrados = new HashSet<>();

        if(wordOptional.isPresent()){
            encontrados.addAll(menuRepository.findMenusByNombreContainingIgnoreCase(wordOptional.get()));
            encontrados.addAll(menuRepository.findMenusByPrimeroContainingIgnoreCase(wordOptional.get()));
            encontrados.addAll(menuRepository.findMenusBySegundoContainingIgnoreCase(wordOptional.get()));
            encontrados.addAll(menuRepository.findMenusByPostreContainingIgnoreCase(wordOptional.get()));
        }

        return new ArrayList<>(encontrados);
    }

    public Menu save(Menu menu){
        return this.menuRepository.save(menu);
    }

    public Menu one(Long id){
        return this.menuRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Menu.class));
    }

    // ¿TENGO QUE GUARDAR BODA PARA ACTUALIZAR SU COLECCION DE MENUS?
    public Menu replace(Long id, Menu menu){
        return this.menuRepository.findById(id).map( m -> {
            if (id.equals(menu.getId())) return this.menuRepository.save(menu);
            else throw new NotCouplingIdException(id, menu.getId(), Menu.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Menu.class));
    }

    public void delete(Long id){
        this.menuRepository.findById(id).map( m -> {this.menuRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Menu.class));
    }
}
