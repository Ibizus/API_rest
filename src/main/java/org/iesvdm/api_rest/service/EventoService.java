package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Evento;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    public List<Evento> all(){return this.eventoRepository.findAll();}

    public Evento save(Evento evento){
        return this.eventoRepository.save(evento);
    }

    public Evento one(Long id){
        return this.eventoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Evento.class));
    }

    public Evento replace(Long id, Evento evento){
        return this.eventoRepository.findById(id).map( m -> {
            if (id.equals(evento.getId())) return this.eventoRepository.save(evento);
            else throw new NotCouplingIdException(id, evento.getId(), Evento.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Evento.class));
    }

    public void delete(Long id){
        this.eventoRepository.findById(id).map( m -> {this.eventoRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Evento.class));
    }
}
