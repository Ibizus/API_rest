package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Tarea;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TareaService {

    @Autowired
    TareaRepository tareaRepository;

    public List<Tarea> all(){return this.tareaRepository.findAll();}

    public Tarea save(Tarea tarea){
        return this.tareaRepository.save(tarea);
    }

    public Tarea one(Long id){
        return this.tareaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Tarea.class));
    }

    public Tarea replace(Long id, Tarea tarea){
        return this.tareaRepository.findById(id).map( m -> {
            if (id.equals(tarea.getId())) return this.tareaRepository.save(tarea);
            else throw new NotCouplingIdException(id, tarea.getId(), Tarea.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Tarea.class));
    }

    public void delete(Long id){
        this.tareaRepository.findById(id).map( m -> {this.tareaRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Tarea.class));
    }
}
