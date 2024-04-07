package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Invitacion;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.InvitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InvitacionService {

    @Autowired
    InvitacionRepository invitacionRepository;

    public List<Invitacion> all(){return this.invitacionRepository.findAll();}

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
