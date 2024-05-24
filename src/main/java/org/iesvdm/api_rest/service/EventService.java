package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Event;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> all(){return this.eventRepository.findAll();}

    public Event save(Event event){
        return this.eventRepository.save(event);
    }

    public Event one(Long id){
        return this.eventRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Event.class));
    }

    public Event replace(Long id, Event event){
        return this.eventRepository.findById(id).map( m -> {
            if (id.equals(event.getId())) return this.eventRepository.save(event);
            else throw new NotCouplingIdException(id, event.getId(), Event.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Event.class));
    }

    public void delete(Long id){
        this.eventRepository.findById(id).map( m -> {this.eventRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Event.class));
    }
}
