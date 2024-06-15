package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Event;
import org.iesvdm.api_rest.domain.Wedding;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.EventRepository;
import org.iesvdm.api_rest.repository.WeddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    WeddingRepository weddingRepository;

    public List<Event> all(){return this.eventRepository.findAll();}

    public List<Event> allByWeddingId(long id){
        return this.eventRepository.findByWedding_Id(id);}

    public Event one(Long id){
        return this.eventRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Event.class));
    }

    public Event save(Long id, Event event){
        Wedding wedding = weddingRepository.findById(id).get();
        event.setWedding(wedding);
        return this.eventRepository.save(event);
    }

    public Event replace(Long id, Event event){
        return this.eventRepository.findById(id).map( m -> {
            if (id.equals(event.getId())){
                Wedding wedding = weddingRepository.findWeddingByEvents_Id(id);
                event.setWedding(wedding);
                return this.eventRepository.save(event);
            }
            else throw new NotCouplingIdException(id, event.getId(), Event.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Event.class));
    }

    public void delete(Long id){
        this.eventRepository.findById(id).map( m -> {this.eventRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Event.class));
    }
}
