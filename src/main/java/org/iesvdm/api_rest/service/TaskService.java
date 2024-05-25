package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Task;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public List<Task> all(){return this.taskRepository.findAll();}

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
