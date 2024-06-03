package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.User;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
