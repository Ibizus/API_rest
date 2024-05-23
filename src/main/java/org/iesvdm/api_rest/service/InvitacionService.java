package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Invitacion;
import org.iesvdm.api_rest.domain.Regalo;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.InvitacionRepository;
import org.iesvdm.api_rest.util.PaginationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InvitacionService {

    @Autowired
    InvitacionRepository invitacionRepository;

    public List<Invitacion> all(){return this.invitacionRepository.findAll();}

    // Pagination of All data:
    public Map<String, Object> all(int page, int size){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Invitacion> pageAll = this.invitacionRepository.findAll(paginator);

        return PaginationTool.createPaginatedResponseMap(pageAll, "invitaciones");
    }

    // Find by filter and return paginated:
    public Map<String, Object> findByFilter(int page, int size, String filter){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Invitacion> pageFiltered = this.invitacionRepository
                .findInvitacionByNombreInvitadoContainingIgnoreCase(filter, paginator);

        return PaginationTool.createPaginatedResponseMap(pageFiltered, "invitaciones");
    }

    public Invitacion save(Invitacion invitacion){
        return this.invitacionRepository.save(invitacion);
    }

    public Invitacion one(Long id){
        return this.invitacionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Invitacion.class));
    }

    public Invitacion replace(Long id, Invitacion invitacion){
        return this.invitacionRepository.findById(id).map( m -> {
            if (id.equals(invitacion.getId())) return this.invitacionRepository.save(invitacion);
            else throw new NotCouplingIdException(id, invitacion.getId(), Invitacion.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Invitacion.class));
    }

    public void delete(Long id){
        this.invitacionRepository.findById(id).map( m -> {this.invitacionRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Invitacion.class));
    }
}
