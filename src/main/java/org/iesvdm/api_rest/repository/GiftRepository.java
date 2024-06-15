package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Gift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftRepository extends JpaRepository <Gift, Long> {

    public Page<Gift> findGiftByNameContainingIgnoreCase(String name, Pageable pageable);

    public Page<Gift> findGiftsByNameContainingIgnoreCaseAndWedding_Id(String name, Long id, Pageable pageable);

    public Page<Gift> findByWedding_Id(Long id, Pageable pageable);

}
