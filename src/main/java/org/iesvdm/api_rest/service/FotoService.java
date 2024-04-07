package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Foto;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FotoService {

    @Autowired
    FotoRepository fotoRepository;

    public List<Foto> all(){return this.fotoRepository.findAll();}

    public Foto save(Foto foto){
        return this.fotoRepository.save(foto);
    }

    public Foto one(Long id){
        return this.fotoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Foto.class));
    }

    public Foto replace(Long id, Foto foto){
        return this.fotoRepository.findById(id).map( m -> {
            if (id.equals(foto.getId())) return this.fotoRepository.save(foto);
            else throw new NotCouplingIdException(id, foto.getId(), Foto.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Foto.class));
    }

    public void delete(Long id){
        this.fotoRepository.findById(id).map( m -> {this.fotoRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Foto.class));
    }
}
