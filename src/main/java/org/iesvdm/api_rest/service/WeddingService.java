package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Wedding;
import org.iesvdm.api_rest.domain.Wedding;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.WeddingRepository;
import org.iesvdm.api_rest.repository.UserRepository;
import org.iesvdm.api_rest.util.PaginationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WeddingService {

    @Autowired
    private WeddingRepository weddingRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Wedding> all(){return this.weddingRepository.findAll();}

    // Pagination of All data:
    public Map<String, Object> all(int page, int size){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Wedding> pageAll = this.weddingRepository.findAll(paginator);

        return PaginationTool.createPaginatedResponseMap(pageAll, "weddings");
    }

    // Find by filter and return paginated:
    public Map<String, Object> findByFilter(int page, int size, String filter){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Wedding> pageFiltered = this.weddingRepository
                .findByNameContainingIgnoreCaseOrPartner1nameContainingIgnoreCaseOrPartner2nameContainingIgnoreCase
                        (filter, filter, filter, paginator);

        return PaginationTool.createPaginatedResponseMap(pageFiltered, "weddings");
    }

    public Map<String, Object> findByUser(Long user){
        Pageable paginator = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        Page<Wedding> pageAll = this.weddingRepository.findWeddingByUser_Id(user, paginator);
        return PaginationTool.createPaginatedResponseMap(pageAll, "weddings");
    }

    public Wedding save(Wedding wedding){return this.weddingRepository.save(wedding);}

    public Wedding one(Long id){
        return this.weddingRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Wedding.class));
    }

    public Wedding replace(Long id, Wedding wedding){
        return this.weddingRepository.findById(id).map(b -> {
            if (id.equals(wedding.getId())) return this.weddingRepository.save(wedding);
            else throw new NotCouplingIdException(id, wedding.getId(), Wedding.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Wedding.class));
    }

    public void delete(Long id){
        this.weddingRepository.findById(id).map(b -> {this.weddingRepository.delete(b);
        return b;})
                .orElseThrow(()-> new EntityNotFoundException(id, Wedding.class));
    }
}