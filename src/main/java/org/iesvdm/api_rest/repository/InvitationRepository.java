package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Invitation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository <Invitation, Long> {

    public Page<Invitation> findInvitationByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT u.email FROM User u " +
            "JOIN Wedding w ON w.user.id = u.id " +
            "JOIN Invitation i ON i.wedding.id = w.id " +
            "WHERE i.id = :invitationId")
    Optional<String> findUserEmailByInvitationId(@Param("invitationId") Long invitationId);

    @Query("SELECT w.id FROM Wedding w " +
            "JOIN Invitation i ON i.wedding.id = w.id " +
            "WHERE i.id = :invitationId")
    Optional<Long> findWeddingIdByInvitationId(@Param("invitationId") Long invitationId);
}
