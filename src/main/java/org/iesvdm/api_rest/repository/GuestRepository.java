package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository <Guest, Long> {

    public Page<Guest> findGuestByNameContainingIgnoreCaseOrLastName1ContainingIgnoreCaseOrLastName2ContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase
            (String name, String lastname1, String lastname2, String email, String phone, Pageable pageable);
}
