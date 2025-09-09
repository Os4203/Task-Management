package com.eastnets.taskmanagement.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.eastnets.taskmanagement.manager.model.*;
import com.eastnets.taskmanagement.manager.repository.TaskRepo;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepo taskRepository;
    public void save(Tasks task){
        taskRepository.save(task);
    }
    public void update(Tasks task){
        taskRepository.save(task);
    }
    public Tasks findById(int id){
        return taskRepository.findById(id).orElse(null);
    }
    public List<Tasks> findAll(){
        return taskRepository.findAll();
    }
    public void deleteById(int id){
        taskRepository.deleteById(id);
    }
    public void deleteAll(){
        taskRepository.deleteAll();
    }
}
