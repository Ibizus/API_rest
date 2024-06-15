package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.User;
import org.iesvdm.api_rest.domain.User;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
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
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> all(){
        return this.userRepository.findAll();}

    public Page<User> findByName(String filter){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        return this.userRepository.findUsersByUsernameContainingIgnoreCase(filter, pageable);
    }

    public Page<User> findByLastname(String filter){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "lastname1"));
        return this.userRepository.findUsersByLastName1ContainingIgnoreCaseOrLastName2ContainingIgnoreCase(filter, filter, pageable);
    }

    // Pagination of All data:
    public Map<String, Object> all(int page, int size){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").descending());
        Page<User> pageAll = this.userRepository.findAll(paginator);

        return PaginationTool.createPaginatedResponseMap(pageAll, "users");
    }

    // Find by filter and return paginated:
    public Map<String, Object> findByFilter(int page, int size, String filter){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").descending());
        Page<User> pageFiltered = this.userRepository
                .findUsersByUsernameContainingIgnoreCaseOrLastName1ContainingIgnoreCaseOrLastName2ContainingIgnoreCase
                        (filter, filter, filter, paginator);

        return PaginationTool.createPaginatedResponseMap(pageFiltered, "users");
    }

    public User save(User user){return this.userRepository.save(user);}

    public User one(Long id){
        return this.userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, User.class));
    }

    public User replace(Long id, User user){
        return this.userRepository.findById(id).map(u -> {
            if (id.equals(user.getId())) return this.userRepository.save(user);
            else throw new NotCouplingIdException(id, user.getId(), User.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, User.class));
    }

    public void delete(Long id){
        this.userRepository.findById(id).map(u -> {this.userRepository.delete(u);
                    return u;})
                .orElseThrow(()-> new EntityNotFoundException(id, User.class));
    }
}
