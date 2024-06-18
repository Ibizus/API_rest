package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Menu;
import org.iesvdm.api_rest.domain.Menu;
import org.iesvdm.api_rest.domain.Wedding;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.WeddingRepository;
import org.iesvdm.api_rest.repository.MenuRepository;
import org.iesvdm.api_rest.util.PaginationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {

    @Autowired
    WeddingRepository weddingRepository; //¿NECESARIO?
    @Autowired
    MenuRepository menuRepository;

    public List<Menu> all(){return this.menuRepository.findAll();}

    // Pagination of All data by Wedding id:
    public Map<String, Object> allByWeddingId(long id, int page, int size){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Menu> pageAll = this.menuRepository.findByWedding_Id(id, paginator);

        System.out.println("Inside allByWeddingId method in Menu Service");
        System.out.println("PAGINATED RESULT" + pageAll);
        return PaginationTool.createPaginatedResponseMap(pageAll, "menus");
    }

    // Find Wedding's menus by filter and return paginated:
    public Map<String, Object> findByWeddingIdAndFilter(long id, int page, int size, String filter){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Menu> pageFiltered = this.menuRepository
                .findMenusByNameContainingIgnoreCaseOrStarterContainingIgnoreCaseOrMaincourseContainingIgnoreCaseOrDessertContainingIgnoreCaseAndWedding_Id(filter, filter, filter, filter, id, paginator);

        return PaginationTool.createPaginatedResponseMap(pageFiltered, "menus");
    }

//    public List<Menu> filter(Optional<String> wordOptional){
//        List<Menu> resultado = new ArrayList<>();
//        if(wordOptional.isPresent()) {
//            resultado = menuRepository.findMenusByNameContainingIgnoreCaseOrStarterContainingIgnoreCaseOrMainCourseContainingIgnoreCaseOrDessertContainingIgnoreCase(wordOptional.get(), wordOptional.get(), wordOptional.get(), wordOptional.get());
//        }
//        return resultado;
//    }

    public Menu one(Long id){
        return this.menuRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Menu.class));
    }

    public Menu save(Long id, Menu menu){
        Wedding wedding = weddingRepository.findById(id).get();
        menu.setWedding(wedding);
        return this.menuRepository.save(menu);
    }

    public Menu replace(Long id, Menu menu){
        return this.menuRepository.findById(id).map( m -> {
            if (id.equals(menu.getId())){
                Wedding wedding = weddingRepository.findWeddingByMenus_Id(id);
                menu.setWedding(wedding);
                return this.menuRepository.save(menu);
            }
            else throw new NotCouplingIdException(id, menu.getId(), Menu.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Menu.class));
    }

    public void delete(Long id){
        this.menuRepository.findById(id).map( m -> {this.menuRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Menu.class));
    }
}
