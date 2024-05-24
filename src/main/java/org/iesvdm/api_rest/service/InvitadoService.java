package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Invitacion;
import org.iesvdm.api_rest.domain.Invitado;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.InvitadoRepository;
import org.iesvdm.api_rest.util.PaginationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InvitadoService {

    @Autowired
    InvitadoRepository invitadoRepository;

    public List<Invitado> all(){return this.invitadoRepository.findAll();}

    // Pagination of All data:
    public Map<String, Object> all(int page, int size){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Invitado> pageAll = this.invitadoRepository.findAll(paginator);

        return PaginationTool.createPaginatedResponseMap(pageAll, "invitaciones");
    }

    // Find by filter and return paginated:
    public Map<String, Object> findByFilter(int page, int size, String filter){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Invitado> pageFiltered = this.invitadoRepository
                .findInvitadoByNombreContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefonoContainingIgnoreCase
                        (filter, filter, filter, filter, filter, paginator);

        return PaginationTool.createPaginatedResponseMap(pageFiltered, "invitaciones");
    }

    public Invitado save(Invitado invitado){
        return this.invitadoRepository.save(invitado);
    }

    public Invitado one(Long id){
        return this.invitadoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Invitado.class));
    }

    public Invitado replace(Long id, Invitado invitado){
        return this.invitadoRepository.findById(id).map( m -> {
            if (id.equals(invitado.getId())) return this.invitadoRepository.save(invitado);
            else throw new NotCouplingIdException(id, invitado.getId(), Invitado.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Invitado.class));
    }

    public void delete(Long id){
        this.invitadoRepository.findById(id).map( m -> {this.invitadoRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Invitado.class));
    }
}
