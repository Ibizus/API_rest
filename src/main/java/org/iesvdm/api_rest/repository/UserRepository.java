package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Page<User> findUsersByUsernameContainingIgnoreCase(String name, Pageable pageable);

    public Page<User> findUsersByLastName1ContainingIgnoreCaseOrLastName2ContainingIgnoreCase(String lastname1, String lastname2, Pageable pageable);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
