package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Task;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.TaskRepository;
import org.iesvdm.api_rest.util.PaginationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public List<Task> all(){return this.taskRepository.findAll();}

    // Pagination of All data by Wedding id:
    public Map<String, Object> allByWeddingId(long id, int page, int size){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Task> pageAll = this.taskRepository.findByWedding_Id(id, paginator);

        return PaginationTool.createPaginatedResponseMap(pageAll, "tasks");
    }

    // Find Wedding's tasks by filter and return paginated:
    public Map<String, Object> findByWeddingIdAndFilter(long id, int page, int size, String filter){
        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Task> pageFiltered = this.taskRepository
                .findTaskByDescriptionContainingIgnoreCaseAndWedding_Id(filter, id, paginator);

        return PaginationTool.createPaginatedResponseMap(pageFiltered, "tasks");
    }

//    // Pagination of All data:
//    public Map<String, Object> all(int page, int size){
//        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
//        Page<Task> pageAll = this.taskRepository.findAll(paginator);
//
//        return PaginationTool.createPaginatedResponseMap(pageAll, "tasks");
//    }
//
//    // Find by filter and return paginated:
//    public Map<String, Object> findByFilter(int page, int size, String filter){
//        Pageable paginator = PageRequest.of(page, size, Sort.by("id").ascending());
//        Page<Task> pageFiltered = this.taskRepository
//                .findTaskByDescriptionContainingIgnoreCase(filter, paginator);
//
//        return PaginationTool.createPaginatedResponseMap(pageFiltered, "tasks");
//    }

    public Task save(Task task){
        return this.taskRepository.save(task);
    }

    public Task one(Long id){
        return this.taskRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Task.class));
    }

    public Task replace(Long id, Task task){
        return this.taskRepository.findById(id).map(m -> {
            if (id.equals(task.getId())) return this.taskRepository.save(task);
            else throw new NotCouplingIdException(id, task.getId(), Task.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Task.class));
    }

    public void delete(Long id){
        this.taskRepository.findById(id).map(m -> {this.taskRepository.delete(m);
                    return m;})
                .orElseThrow(()-> new EntityNotFoundException(id, Task.class));
    }
}
