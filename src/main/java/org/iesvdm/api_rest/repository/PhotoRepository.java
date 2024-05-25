package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository <Photo, Long> {
}
