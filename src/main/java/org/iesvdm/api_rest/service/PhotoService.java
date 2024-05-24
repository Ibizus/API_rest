package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Photo;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> all(){return this.photoRepository.findAll();}

    public Photo save(Photo photo){
        return this.photoRepository.save(photo);
    }

    public Photo one(Long id){
        return this.photoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Photo.class));
    }

    public Photo replace(Long id, Photo photo){
        return this.photoRepository.findById(id).map(m -> {
            if (id.equals(photo.getId())) return this.photoRepository.save(photo);
            else throw new NotCouplingIdException(id, photo.getId(), Photo.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Photo.class));
    }

    public void delete(Long id){
        this.photoRepository.findById(id).map(m -> {this.photoRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Photo.class));
    }
}
