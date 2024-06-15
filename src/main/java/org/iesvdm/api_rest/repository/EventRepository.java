package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository <Event, Long> {

    public List<Event> findByWedding_Id(Long id);
}
