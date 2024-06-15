package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Wedding;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeddingRepository extends JpaRepository<Wedding, Long> {

    public Page<Wedding> findByNameContainingIgnoreCaseOrPartner1NameContainingIgnoreCaseOrPartner2NameContainingIgnoreCase(String name, String partner1name, String partner2name, Pageable pageable);

    public Page<Wedding> findWeddingByUser_Id(Long id, Pageable pageable);

    public Wedding findWeddingByGifts_Id(Long id);

    public Wedding findWeddingByTasks_Id(Long id);

    public Wedding findWeddingByInvitations_Id(Long id);

    public Wedding findWeddingByMenus_Id(Long id);

    public Wedding findWeddingByEvents_Id(Long id);

}
