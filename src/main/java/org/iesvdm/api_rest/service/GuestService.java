package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Guest;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.GuestRepository;
import org.iesvdm.api_rest.util.PaginationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    public List<Guest> all(){return this.guestRepository.findAll();}

    // Pagination of All data:
    public Map<String, Object> all(int page, int size){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Guest> pageAll = this.guestRepository.findAll(paginator);

        return PaginationTool.createPaginatedResponseMap(pageAll, "invitations");
    }

    // Find by filter and return paginated:
    public Map<String, Object> findByFilter(int page, int size, String filter){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Guest> pageFiltered = this.guestRepository
                .findGuestByNameContainingIgnoreCaseOrLastName1ContainingIgnoreCaseOrLastName2ContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase
                        (filter, filter, filter, filter, filter, paginator);

        return PaginationTool.createPaginatedResponseMap(pageFiltered, "invitations");
    }

    public Guest save(Guest guest){
        return this.guestRepository.save(guest);
    }

    public Guest one(Long id){
        return this.guestRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Guest.class));
    }

    public Guest replace(Long id, Guest guest){
        return this.guestRepository.findById(id).map(m -> {
            if (id.equals(guest.getId())) return this.guestRepository.save(guest);
            else throw new NotCouplingIdException(id, guest.getId(), Guest.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Guest.class));
    }

    public void delete(Long id){
        this.guestRepository.findById(id).map(m -> {this.guestRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Guest.class));
    }
}
