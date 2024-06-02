package org.iesvdm.api_rest.repository;

import org.iesvdm.api_rest.domain.Gift;
import org.iesvdm.api_rest.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {

    public Page<Task> findTaskByDescriptionContainingIgnoreCase(String name, Pageable pageable);
}
