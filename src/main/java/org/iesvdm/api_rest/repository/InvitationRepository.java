package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Invitation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository <Invitation, Long> {

    public Page<Invitation> findInvitationByNameContainingIgnoreCase(String name, Pageable pageable);
}
