package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Gift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepository extends JpaRepository <Gift, Long> {

    public Page<Gift> findGiftByNameContainingIgnoreCase(String name, Pageable pageable);
}
