package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Regalo;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.RegaloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegaloService {

    @Autowired
    RegaloRepository regaloRepository;

    public List<Regalo> all(){return this.regaloRepository.findAll();}

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
