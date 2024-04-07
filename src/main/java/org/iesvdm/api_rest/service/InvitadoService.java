package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Invitado;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.InvitadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InvitadoService {

    @Autowired
    InvitadoRepository invitadoRepository;

    public List<Invitado> all(){return this.invitadoRepository.findAll();}

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
