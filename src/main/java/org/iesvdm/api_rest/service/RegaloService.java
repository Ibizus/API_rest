package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Regalo;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.RegaloRepository;
import org.iesvdm.api_rest.util.PaginationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegaloService {

    @Autowired
    RegaloRepository regaloRepository;

    public List<Regalo> all(){return this.regaloRepository.findAll();}

    // Pagination of All data:
    public Map<String, Object> all(int page, int size){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Regalo> pageAll = this.regaloRepository.findAll(paginator);

        return PaginationTool.createPaginatedResponseMap(pageAll, "regalos");
    }

    // Find by filter and return paginated:
    public Map<String, Object> findByFilter(int page, int size, String filter){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Regalo> pageFiltered = this.regaloRepository
                .findRegaloByNombreContainingIgnoreCase(filter, paginator);

        return PaginationTool.createPaginatedResponseMap(pageFiltered, "regalos");
    }

    public Regalo save(Regalo regalo){
        return this.regaloRepository.save(regalo);
    }

    public Regalo one(Long id){
        return this.regaloRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Regalo.class));
    }

    public Regalo replace(Long id, Regalo regalo){
        return this.regaloRepository.findById(id).map( m -> {
            if (id.equals(regalo.getId())) return this.regaloRepository.save(regalo);
            else throw new NotCouplingIdException(id, regalo.getId(), Regalo.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Regalo.class));
    }

    public void delete(Long id){
        this.regaloRepository.findById(id).map( m -> {this.regaloRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Regalo.class));
    }
}
